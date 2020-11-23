package controllers;

import com.griddynamics.controllers.CategoryController;
import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.service.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    private CategoryDTO categoryDTO;

    private CategoryDTO categoryDTOWithoutID;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void beforeAllMethods() {
        categoryDTO = new CategoryDTO(1, "category", null);

        categoryDTOWithoutID = new CategoryDTO(null, "category", null);
    }

    @Test
    public void getAllCategories_CategoriesArePresentInTheDatabase_ShouldReturnCategories() {

        /* Given */
        List<CategoryDTO> categories = Collections.singletonList(categoryDTO);

        ResponseEntity<List<CategoryDTO>> expected =
                new ResponseEntity<>(categories, HttpStatus.OK);

        /* When */
        Mockito.when(categoryService.findAll()).thenReturn(categories);

        /* Then */
        ResponseEntity<List<CategoryDTO>> actual =
                categoryController.getAllCategories();

        Mockito.verify(categoryService).findAll();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllCategories_CategoriesAreNotPresentInTheDatabase_ShouldReturnNotFound() {

        /* Given */
        ResponseEntity<List<CategoryDTO>> expected =
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

        /* When */
        Mockito.when(categoryService.findAll()).thenReturn(null);

        /* Then */
        ResponseEntity<List<CategoryDTO>> actual = categoryController.getAllCategories();

        Mockito.verify(categoryService).findAll();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCategoryById_CategoryIsPresentIdIsValid_ShouldReturnCategory() throws ServiceException{

        /* Given */
        Integer id = 1;
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(categoryDTO, HttpStatus.OK);

        /* When */
        Mockito.when(categoryService.getById(id)).thenReturn(categoryDTO);

        /* Then */
        ResponseEntity<CategoryDTO> actual = categoryController.getCategoryById(id);

        Mockito.verify(categoryService).getById(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCategoryById_IdIsNotValid_ShouldReturnBadRequest() throws ServiceException {

        /* Given */
        Integer id = null;
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        /* When */
        Mockito.when(categoryService.getById(null)).thenThrow(ServiceException.class);

        /* Then */
        ResponseEntity<CategoryDTO> actual = categoryController.getCategoryById(id);

        Mockito.verify(categoryService).getById(null);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCategoryById_CategoryIsNotFound_ShouldReturnNotFound() throws ServiceException {

        /* Given */
        Integer id = 10;
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

        /* When */
        Mockito.when(categoryService.getById(id)).thenReturn(null);

        /* Then */
        ResponseEntity<CategoryDTO> actual = categoryController.getCategoryById(id);

        Mockito.verify(categoryService).getById(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addNewCategory_CategoryIsNotNull_ShouldReturnCategory() throws ServiceException {

        /* Given */
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(categoryDTO, HttpStatus.OK);

        /* When */
        Mockito.when(categoryService.save(categoryDTOWithoutID)).thenReturn(categoryDTO);

        /* Then */
        ResponseEntity<CategoryDTO> actual =
                categoryController.addNewCategory(categoryDTOWithoutID);

        Mockito.verify(categoryService).save(categoryDTOWithoutID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addNewCategory_CategoryIsNull_ShouldReturnBadRequest() throws ServiceException {

        /* Given */
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        /* When */
        Mockito.when(categoryService.save(null)).thenThrow(ServiceException.class);

        /* Then */
        ResponseEntity<CategoryDTO> actual =
                categoryController.addNewCategory(null);

        Mockito.verify(categoryService).save(null);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateCategory_CategoryIsNotNull_ShouldReturnCategory() throws ServiceException {

        /* Given */
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(categoryDTO, HttpStatus.OK);

        /* When */
        Mockito.when(categoryService.update(categoryDTO)).thenReturn(categoryDTO);

        /* Then */
        ResponseEntity<CategoryDTO> actual =
                categoryController.updateCategory(categoryDTO);

        Mockito.verify(categoryService).update(categoryDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateCategory_CategoryIsNull_ShouldReturnBadRequest() throws ServiceException {

        /* Given */
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        /* When */
        Mockito.when(categoryService.update(null)).thenThrow(ServiceException.class);

        /* Then */
        ResponseEntity<CategoryDTO> actual =
                categoryController.updateCategory(null);


        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteCategoryById_IdIsNotNull_ShouldDeleteEntity() throws ServiceException {

        /* Given */
        Integer id = 1;
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

        /* Then */
        ResponseEntity<CategoryDTO> actual =
                categoryController.deleteCategoryById(id);

        Mockito.verify(categoryService).deleteById(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteCategoryById_IdIsNull_ShouldReturnBadRequest() throws ServiceException {

        /* Given */
        Integer id = null;
        ResponseEntity<CategoryDTO> expected =
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        /* When */
        Mockito.doThrow(ServiceException.class).when(categoryService).deleteById(null);

        /* Then */
        ResponseEntity<CategoryDTO> actual =
                categoryController.deleteCategoryById(id);

        Assert.assertEquals(expected, actual);
    }
}
