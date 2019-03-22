package org.springframework.samples.petclinic.vet;

import org.junit.Test;
import org.junit.Before;
import org.springframework.samples.petclinic.mysql.domain.Specialty;
import org.springframework.samples.petclinic.mysql.domain.Vet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VetsTests {

    private Vet james;
    private Vet helen;

    @Before
    public void setup() {
        this.james = new Vet();
        this.james.setFirstName("James");
        this.james.setLastName("Carter");
        this.james.setId(1);

        this.helen = new Vet();
        this.helen.setFirstName("Helen");
        this.helen.setLastName("Leary");
        this.helen.setId(2);

        Specialty radiology = new Specialty();
        radiology.setId(1);
        radiology.setName("radiology");
        this.helen.addSpecialty(radiology);
    }

    @Test
    public void testArrayList() {
        List<Vet> list = new ArrayList<>();
        list.add(this.james);
        list.add(this.helen);

        VetsInterface vets = new Vets(list);

        assertThat(vets.getVetList().get(0).getId()).isEqualTo(james.getId());
        assertThat(vets.getVetList().get(0).getFirstName()).isEqualTo(james.getFirstName());
        assertThat(vets.getVetList().get(0).getLastName()).isEqualTo(james.getLastName());
    }

    @Test
    public void testLinkedList() {
        List<Vet> list = new LinkedList<>();
        list.add(this.james);
        list.add(this.helen);

        VetsInterface vets = new Vets(list);

        assertThat(vets.getVetList().get(0).getId()).isEqualTo(james.getId());
        assertThat(vets.getVetList().get(0).getFirstName()).isEqualTo(james.getFirstName());
        assertThat(vets.getVetList().get(0).getLastName()).isEqualTo(james.getLastName());
    }

    @Test
    public void testMockedLinkedList() {
        List<Vet> list = (LinkedList<Vet>) mock(LinkedList.class);
        list.add(this.james);
        list.add(this.helen);
        
        when(list.get(0)).thenReturn(this.james);

        VetsInterface vets = new Vets(list);

        verify(list).add(this.james);
        verify(list).add(this.helen);
        assertThat(vets.getVetList().get(0).getId()).isEqualTo(james.getId());
        assertThat(vets.getVetList().get(0).getFirstName()).isEqualTo(james.getFirstName());
        assertThat(vets.getVetList().get(0).getLastName()).isEqualTo(james.getLastName());
    }

}
