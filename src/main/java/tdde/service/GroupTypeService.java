package tdde.service;;


import tdde.domain.GroupType;

import java.util.List;
import java.util.Map;

public interface GroupTypeService {
    GroupType save(GroupType groupType);

    void delete(GroupType groupType) throws Exception;

    GroupType findOne(Long id);

    GroupType findOne(String id);

    Iterable<GroupType> findAll();

    List<GroupType> findByLabel(String label);
    List<GroupType> addGroupTypes(Map<String, String> groupTypes);

    List<GroupType> findAllGroupTyps();
}
