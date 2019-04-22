/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.system.toggles.Toggles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
class VetController {

    private final VetRepository vets;
    private int vetList = 0;
    private int vetListInsurance = 0;
    private Logger logger = LogManager.getLogger("VetController");


    public VetController(VetRepository clinicService) {
        this.vets = clinicService;
    }

    @GetMapping("/vets.html")
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        model.put("vets", vets);
        if(Toggles.insuranceRequired == true)
        {
            logger.info("Insurance is shown");
            return "vets/vetListInsurance";
        }
        else
        {
            logger.info("Insurance is not shown");
            return "vets/vetList";
        }
    }

    @GetMapping({ "/vets" })
    public @ResponseBody Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        return vets;
    }

    @GetMapping("/promotions.html")
    public String showPromotions(){

        if (VetToggles.togglePromotions == true)
        {
            return "promo/promotions.html";
        } else {
            throw new RuntimeException("No promotions");
        }


    }

    @GetMapping("/promotionB.html")
    public String showPromotionB(){

        if (VetToggles.togglePromotionB == true)
        {
            return "promo/promotionB.html";
        } else {
            throw new RuntimeException("No promotions");
        }


    }

    public void loggingAccess(){
        logger.info("Page with insurance accessed: "+ vetListInsurance);
        logger.info("Page with no insurance accessed: "+ vetList);

    }

    public void countvetList(){vetList++;}
    public void countVetListInsurance(){vetListInsurance++;}
    public int getCountVetList(){return vetList;}
    public int getCountVetListInsurance(){return vetListInsurance;}
}
