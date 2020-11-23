package services;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.entities.Category;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.exceptions.ValidationException;
import com.griddynamics.mappers.CategoryMapper;
import com.griddynamics.repositories.CategoryRepository;
import com.griddynamics.service.CategoryService;
import com.griddynamics.validators.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    private Category category;

    private Category categoryWithoutId;

    private CategoryDTO categoryDTO;

    private CategoryDTO categoryDTOWithoutId;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private Validator<CategoryDTO> validator;

    @InjectMocks
    private CategoryService categoryService;

    @Before
    public void beforeAllMethods() {
        category = new Category(1, "category", null);

        categoryWithoutId = new Category("category", null);

        categoryDTO = new CategoryDTO(category);

        categoryDTOWithoutId = new CategoryDTO(categoryWithoutId);
    }

    @Test
    public void findAll_ShouldReturnAllCategories() {

        /* Given */
        List<CategoryDTO> expected = Collections.singletonList(categoryDTO);
        Iterable<Category> iterable = Collections.singletonList(category);

        /* When */
        Mockito.when(categoryRepository.findAll()).thenReturn(iterable);
        Mockito.when(categoryMapper.mapDTOList(iterable)).thenReturn(expected);

        /* Then */
        List<CategoryDTO> actual = categoryService.findAll();

        Mockito.verify(categoryRepository).findAll();
        Mockito.verify(categoryMapper).mapDTOList(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getById_CategoryIsPresent_ShouldReturnCategory() throws ServiceException {

        /* Given */
        Integer id = 1;

        Optional<Category> optional = Optional.of(category);

        /* When */
        Mockito.when(categoryRepository.findById(id)).thenReturn(optional);
        Mockito.when(categoryMapper.mapDTO(optional.get())).thenReturn(categoryDTO);

        /* Then */
        CategoryDTO actual = categoryService.getById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(categoryRepository).findById(id);
        Mockito.verify(categoryMapper).mapDTO(category);
        Assert.assertEquals(categoryDTO, actual);
    }

    @Test
    public void getById_CategoryIsNotPresent_ShouldReturnNull() throws ServiceException {

        /* Given */
        Integer id = 2;

        Optional<Category> optional = Optional.empty();

        /* When */
        Mockito.when(categoryRepository.findById(id)).thenReturn(optional);

        /* Then */
        CategoryDTO actual = categoryService.getById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(categoryRepository).findById(id);
        Mockito.verify(categoryMapper, never()).mapDTO(category);
        Assert.assertNull(actual);
    }

    @Test
    public void save_ShouldReturnSavedProductWithId() throws ServiceException {

        /* When */
        Mockito.when(categoryMapper.mapEntity(categoryDTOWithoutId)).thenReturn(categoryWithoutId);
        Mockito.when(categoryRepository.save(categoryWithoutId)).thenReturn(category);
        Mockito.when(categoryMapper.mapDTO(category)).thenReturn(categoryDTO);

        /* Then */
        CategoryDTO actual = categoryService.save(categoryDTOWithoutId);

        Mockito.verify(validator).validateDTO(categoryDTOWithoutId);
        Mockito.verify(categoryRepository).save(categoryWithoutId);
        Mockito.verify(categoryMapper).mapEntity(categoryDTOWithoutId);
        Mockito.verify(categoryMapper).mapDTO(category);
        Assert.assertEquals(categoryDTO, actual);
    }

    @Test
    public void update_CategoryIsPresentInTheDatabase_ShouldReturnUpdatedCategory() throws ServiceException {

        /* Given */
        Optional<Category> optional = Optional.of(category);

        /* When */
        Mockito.when(categoryRepository.findById(categoryDTO.getId())).thenReturn(optional);
        Mockito.when(categoryMapper.mapUpdate(categoryDTO, category)).thenReturn(category);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Mockito.when(categoryMapper.mapDTO(category)).thenReturn(categoryDTO);

        /* Then */
        CategoryDTO actual = categoryService.update(categoryDTO);

        Mockito.verify(validator).validateId(categoryDTO.getId());
        Mockito.verify(categoryRepository).findById(categoryDTO.getId());
        Mockito.verify(categoryRepository).save(category);
        Mockito.verify(categoryMapper).mapUpdate(categoryDTO, category);
        Mockito.verify(categoryMapper).mapDTO(category);
        Assert.assertEquals(categoryDTO, actual);
    }

    @Test(expected = ServiceException.class)
    public void update_CategoryIsNotPresentInTheDatabase_ShouldThrowServiceException() throws ServiceException {

        /* Given */
        CategoryDTO updateCategory = new CategoryDTO(2, "category", null);

        /* When */
        Mockito.when(categoryRepository.findById(updateCategory.getId())).thenReturn(Optional.empty());

        /* Then */
        categoryService.update(updateCategory);

        Mockito.verify(validator).validateDTO(updateCategory);
        Mockito.verify(categoryRepository).findById(updateCategory.getId());
        Mockito.verify(categoryRepository, never()).save(Mockito.any(Category.class));
        Mockito.verify(categoryMapper, never())
                .mapUpdate(Mockito.any(CategoryDTO.class), Mockito.any(Category.class));
        Mockito.verify(categoryMapper, never()).mapDTO(Mockito.any(Category.class));
    }

    @Test
    public void deleteById_CategoryIsPresentInTheDatabase_ShouldReturnNothing() throws ServiceException {

        /* Given */
        Integer id = 1;

        Optional<Category> optional = Optional.of(category);

        /* When */
        Mockito.when(categoryRepository.findById(id)).thenReturn(optional);

        /* Then */
        categoryService.deleteById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(categoryRepository).findById(id);
        Mockito.verify(categoryRepository).deleteById(id);
    }

    @Test(expected = ServiceException.class)
    public void deleteById_CategoryIsNotPresentInTheDatabase_ShouldThrowServiceException() throws ServiceException {

        /* Given */
        Integer id = 2;

        Optional<Category> optional = Optional.empty();

        /* When */
        Mockito.when(categoryRepository.findById(id)).thenReturn(optional);

        /* Then */
        categoryService.deleteById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(categoryRepository).findById(id);
        Mockito.verify(categoryRepository, never()).deleteById(id);
    }
}
