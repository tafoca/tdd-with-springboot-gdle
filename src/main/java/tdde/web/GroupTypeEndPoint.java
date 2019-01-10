package tdde.web;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import tdde.domain.GroupType;
import tdde.service.GroupTypeService;

import java.util.Collections;
import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GroupTypeEndPoint {

    @Autowired
    private GroupTypeService groupTypeService;

    public GroupTypeEndPoint(GroupTypeService groupTypeService) {
        this.groupTypeService =groupTypeService;
    }

    /* public GroupTypeEndPoint() {
     }*/



   @RequestMapping(method = RequestMethod.GET, path = "/groups")
   public MappingJacksonValue findGroupTypeByLabel(@RequestParam String label) {
       List<GroupType>  groupTypes = groupTypeService.findByLabel(label);
       SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("groups");
       FilterProvider listeFilter = new SimpleFilterProvider().addFilter("filtreGroupType", monFiltre);
       MappingJacksonValue grouptypeFiltres = new MappingJacksonValue(groupTypes);
       grouptypeFiltres.setFilters(listeFilter);

       return grouptypeFiltres;
   }

    @GetMapping ("/groupes/{label}")
    public MappingJacksonValue findGroupTypeByLabelpath(@PathVariable String label) {
        List<GroupType>  groupTypes = groupTypeService.findByLabel(label);
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("groups");
        FilterProvider listeFilter = new SimpleFilterProvider().addFilter("filtreGroupType", monFiltre);
        MappingJacksonValue grouptypeFiltres = new MappingJacksonValue(groupTypes);
        grouptypeFiltres.setFilters(listeFilter);

        return grouptypeFiltres;
    }

    @PostMapping("/groups/create")
    public MappingJacksonValue createGroupType(@RequestBody GroupType groupType){
        GroupType groupTypeAdded = this.groupTypeService.save(groupType);
        FilterProvider filters = new SimpleFilterProvider().addFilter("filtreGroupType", SimpleBeanPropertyFilter.serializeAllExcept(Collections.emptySet()));

        ResponseEntity<GroupType> groupTypeResponseEntity = new ResponseEntity<>(groupTypeAdded, HttpStatus.OK);

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("groups");
        FilterProvider listeFilter = new SimpleFilterProvider().addFilter("filtreGroupType", monFiltre);
        MappingJacksonValue grouptypeFiltre = new MappingJacksonValue(groupTypeResponseEntity);
        grouptypeFiltre.setFilters(listeFilter);

        return grouptypeFiltre;
    }

    @PostMapping("/groupes/create")
    public MappingJacksonValue createGroupType2(@RequestBody GroupType groupType){
        GroupType groupTypeAdded = this.groupTypeService.save(groupType);
        FilterProvider filters = new SimpleFilterProvider().addFilter("filtreGroupType", SimpleBeanPropertyFilter.serializeAllExcept(Collections.emptySet()));

        ResponseEntity<GroupType> groupTypeResponseEntity = new ResponseEntity<>(groupTypeAdded, HttpStatus.OK);

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("groups");
        FilterProvider listeFilter = new SimpleFilterProvider().addFilter("filtreGroupType", monFiltre);
        MappingJacksonValue grouptypeFiltre = new MappingJacksonValue(groupType);
        grouptypeFiltre.setFilters(listeFilter);

        return grouptypeFiltre;

    }

    @GetMapping("/groups/show/{id}")
    public MappingJacksonValue showGroupType(@PathVariable long id) throws ClassNotFoundException {

        GroupType groupType = this.groupTypeService.findOne(id);

       // writeFor(groupType.toString());

        if(groupType == null)
            throw new ClassNotFoundException("Le groupTypeService avec l'id "+id+ " est INTROUVABLE Ecran Bleu si je pouvais.");

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAll();
        FilterProvider listeFilter = new SimpleFilterProvider().addFilter("filtreGroupType", monFiltre);
        MappingJacksonValue grouptypeFilter = new MappingJacksonValue(groupType);
        grouptypeFilter.setFilters(listeFilter);

        return grouptypeFilter;
    }

    private void writeFor(String s){
        System.out.println("------------------------------ begin output ------------------------------");
        System.out.println("------->> * "+s+" * <<------- ");
        System.out.println("------------------------------ finish output ------------------------------");
    }

    @DeleteMapping(value= "/groups/delete/{id}")
    public void deleteGroupType(@PathVariable long id) throws Exception {
        this.groupTypeService.delete(groupTypeService.findOne(id));
    }

}
