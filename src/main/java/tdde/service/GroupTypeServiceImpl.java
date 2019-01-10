package tdde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tdde.domain.GroupType;
import tdde.domain.GroupTypeRepository;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class GroupTypeServiceImpl implements GroupTypeService {

    @Autowired
    private GroupTypeRepository groupTypeRepository;

    public GroupTypeServiceImpl(GroupTypeRepository groupTypeRepository) {
        this.groupTypeRepository = groupTypeRepository;
    }

    @Override
    public GroupType save(GroupType groupType) {
        return this.groupTypeRepository.save(groupType);
    }

    @Override
    public void delete(GroupType groupType) throws Exception {
        if(groupTypeRepository.getOne(groupType.getId())!= null)
            this.groupTypeRepository.delete(groupType);
        else
            throw new Exception("Object inexistant");
    }

    @Override
    public GroupType findOne(Long id) {
        if(groupTypeRepository.getOne(id)!= null)
            return this.groupTypeRepository.getOne(id);
        else
        return null;
    }

    @Override
    public GroupType findOne(String id) {
        return this.groupTypeRepository.getOne(Long.valueOf(id));
    }

    @Override
    public Iterable<GroupType> findAll() {
        return this.groupTypeRepository.findAll();
    }

    @Cacheable("Grouptype")
    public List<GroupType> findByLabel(String label) {

        List<GroupType> groupType = groupTypeRepository.findByLabel(label);

        return groupType;
    }

    @Override
    public List<GroupType> addGroupTypes(Map<String, String> groupTypes) {
        return groupTypes.entrySet().stream()
                .map(entry -> new GroupType(entry.getKey(), entry.getValue()))
                .map(groupTypeRepository::save)
                .collect(toList());
    }

    @Override
    public List<GroupType> findAllGroupTyps() {
        return groupTypeRepository.findAll();
    }
}
