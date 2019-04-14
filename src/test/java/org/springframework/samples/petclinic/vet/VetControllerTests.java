package org.springframework.samples.petclinic.vet;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.system.AssignRandomRequirement;
import org.springframework.samples.petclinic.system.toggles.Toggles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link VetController}
 */
@RunWith(SpringRunner.class)
@WebMvcTest(VetController.class)
public class VetControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VetRepository vets;
    private PetRepository petRepository;

    @Mock Map<String, Object> model;


    @Before
    public void setup() {
        Vet james = new Vet();
        james.setFirstName("James");
        james.setLastName("Carter");
        james.setId(1);
        Vet helen = new Vet();
        helen.setFirstName("Helen");
        helen.setLastName("Leary");
        helen.setId(2);
        Specialty radiology = new Specialty();
        radiology.setId(1);
        radiology.setName("radiology");
        helen.addSpecialty(radiology);
        given(this.vets.findAll()).willReturn(Lists.newArrayList(james, helen));
        petRepository = mock(PetRepository.class);
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        model = new HashMap<>();
    }

    @Test
    public void testShowVetListHtml() throws Exception {
        mockMvc.perform(get("/vets.html"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("vets"))
            .andExpect(view().name("vets/vetListInsurance"));
    }

    @Test
    public void testShowResourcesVetList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/vets")
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.vetList[0].id").value(1));
    }

    @Test
    public void testRollback(){
        model.put("vets", vets);
        VetController vetController = new VetController(vets);
        //new feature is on, see insurance page
        Toggles.insuranceRequired = true;
        assertEquals("vets/vetListInsurance", vetController.showVetList(model));

        //new feature is off
        Toggles.insuranceRequired  = false;
        assertEquals("vets/vetList", vetController.showVetList(model));

        //new feature is on again, see insurance page
        Toggles.insuranceRequired  = true;
        assertEquals("vets/vetListInsurance", vetController.showVetList(model));
    }

    @Test
    public void testRandom(){
        int iterations = 1000;
        VetController vetController = new VetController(vets);

        for(int i = 0; i < iterations; i++){
            Toggles.insuranceRequired = AssignRandomRequirement.getRandom(Boolean.TRUE);
            model.put("vets", vets);
            vetController.showVetList(model);
            //new feature is on, see insurance page
            if(Toggles.insuranceRequired) {
                vetController.countVetListInsurance();
            }
            else {
                vetController.countvetList();
            }
            vetController.loggingAccess();
        }
        System.out.println(vetController.getCountVetList());
        System.out.println(vetController.getCountVetListInsurance());
    }
}
