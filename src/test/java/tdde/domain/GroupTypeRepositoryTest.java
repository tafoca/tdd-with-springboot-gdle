package tdde.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tdde.service.GroupTypeService;
import tdde.service.GroupTypeServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class) //@DataJpaTest//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
 public class GroupTypeRepositoryTest {

    @Mock
    private GroupTypeRepository groupTypeRepository;

    private GroupTypeService groupTypeService;


    @Before
    public void setUp() throws Exception {

        groupTypeService = new GroupTypeServiceImpl(groupTypeRepository);

    }

    @Test
    public void ifNoGroupTypesPassedEmptyListIsReturned() {
        assertThat(groupTypeService.addGroupTypes(Collections.emptyMap()), is(empty()));
    }

    @Test
    public void forEveryPairOfTitleAndAuthorGroupTypeIsCreatedAndStored() {
        GroupType first = new GroupType("The first", "author");
        GroupType second = new GroupType("The second", "another author");
        when(groupTypeRepository.save(notNull(GroupType.class))).thenReturn(first).thenReturn(second);

        Map<String, String> books = new HashMap<>();
        books.put("The first", "author");
        books.put("The second", "another author");
        assertThat(groupTypeService.addGroupTypes(books), hasItems(first, second));
    }

    @Test
    public void ifNoGroupTypeFoundForAuthorReturnEmptyList() {
        when(groupTypeRepository.findByLabel("a")).thenReturn(emptyList());

        assertNoGroupTyeFound("a");
        verify(groupTypeRepository, only()).findByLabel("a");
    }

    @Test
    public void booksByAuthorShouldBeCached() {
        GroupType groupType = new GroupType("The book", "author");
        when(groupTypeRepository.findByLabel("a")).thenReturn(singletonList(groupType));
        when(groupTypeRepository.findByLabel("a a")).thenReturn(emptyList());

        assertGroupTypeByLabel("a", groupType);
        assertGroupTypeByLabel("a", groupType);
        assertNoGroupTyeFound("a a");
        verify(groupTypeRepository, times(2)).findByLabel("a");
    }
    // test if methode findone works correcly
    @Test
    public void GroupTypeFindByIdShouldBeCached() {
        GroupType groupType = groupTypeRepository.getOne((long) 18) ;
        when(groupTypeRepository.getOne((long) 18)).thenReturn(groupType);
        assertGroupTypeFound(18,groupType);
    }



    private void assertGroupTypeByLabel(String a, GroupType groupType) {
        assertThat(groupTypeService.findByLabel(a), hasItem(groupType));
    }


    private void assertNoGroupTyeFound(String label) {
        assertThat(groupTypeService.findByLabel(label), is(empty()));
    }

    private void assertGroupTypeFound(long id,GroupType groupType){
        assertThat(groupTypeService.findOne((long) 18), is(groupType));
    }


}