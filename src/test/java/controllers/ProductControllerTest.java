package controllers;

import com.griddynamics.controllers.ProductController;
import com.griddynamics.dto.ProductDTO;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.exceptions.ValidationException;
import com.griddynamics.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private static final ProductDTO PRODUCT = new ProductDTO(1, "testProduct", 12., "description", "brand", "image", null);

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void getAllProductsShouldReturnNullTest() {
        //when
        when(productService.findAll()).thenReturn(null);
        //then
        ResponseEntity<List<ProductDTO>> result = productController.getAllProducts();
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(result.getBody());
        verify(productService).findAll();
    }

    @Test
    public void getAllProductsShouldReturnProductsTest() {
        //when
        when(productService.findAll()).thenReturn(Collections.singletonList(PRODUCT));
        //then
        ResponseEntity<List<ProductDTO>> result = productController.getAllProducts();
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), Collections.singletonList(PRODUCT));
        verify(productService).findAll();
    }

    @Test
    public void getProductByIdShouldThrowServiceException() throws ServiceException {
        //when
        when(productService.getById(null)).thenThrow(ServiceException.class);
        //then
        ResponseEntity<ProductDTO> result = productController.getProductById(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
    }

    @Test
    public void getProductByIdShouldReturnsNullIdTest() throws ServiceException {
        //when
        when(productService.getById(2)).thenReturn(null);
        //then
        ResponseEntity<ProductDTO> result = productController.getProductById(null);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(result.getBody());
        verify(productService, never()).getById(PRODUCT.getId());
    }

    @Test
    public void getProductByIdShouldReturnsNullProductTest() throws ServiceException {
        //when
        when(productService.getById(2)).thenReturn(null);
        //then
        ResponseEntity<ProductDTO> result = productController.getProductById(2);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(result.getBody());
        verify(productService, never()).getById(PRODUCT.getId());
    }

    @Test
    public void getProductByIdShouldReturnsProductTest() throws ServiceException {
        //when
        when(productService.getById(PRODUCT.getId())).thenReturn(PRODUCT);
        //then
        ResponseEntity<ProductDTO> result = productController.getProductById(PRODUCT.getId());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), PRODUCT);
        verify(productService).getById(PRODUCT.getId());
    }

    @Test
    public void addNewProductShouldReturnNullTest() throws ServiceException {
        //when
        when(productService.save(null)).thenThrow(ValidationException.class);
        //then
        ResponseEntity<ProductDTO> result = productController.addNewProduct(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
        verify(productService, never()).save(any(ProductDTO.class));
    }

    @Test
    public void addNewProductShouldReturnProductTest() throws ServiceException {
        //when
        when(productService.save(any(ProductDTO.class))).thenReturn(PRODUCT);
        //then
        ResponseEntity<ProductDTO> result = productController.addNewProduct(PRODUCT);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertEquals(result.getBody(), PRODUCT);
        verify(productService).save(PRODUCT);
    }

    @Test
    public void editProductByIdShouldReturnNullTest() throws ServiceException {
        //when
        when(productService.update(null)).thenThrow(ValidationException.class);
        //then
        ResponseEntity<ProductDTO> result = productController.editProductById(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
        verify(productService, never()).update(any(ProductDTO.class));
    }

    @Test
    public void editProductByIdShouldReturnProductTest() throws ServiceException {
        //when
        when(productService.update(any(ProductDTO.class))).thenReturn(PRODUCT);
        //then
        ResponseEntity<ProductDTO> result = productController.editProductById(PRODUCT);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), PRODUCT);
        verify(productService).update(PRODUCT);
    }

    @Test
    public void deleteProductShouldReturnsNegativeTest() throws ServiceException {
        //when
        doThrow(ValidationException.class).when(productService).deleteById(null);
        //then
        ResponseEntity<ProductDTO> result = productController.deleteProduct(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
        verify(productService, never()).deleteById(PRODUCT.getId());
    }

    @Test
    public void deleteProductShouldReturnsNullProductTest() throws ServiceException {
        ResponseEntity<ProductDTO> result = productController.deleteProduct(2);
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(productService, never()).deleteById(PRODUCT.getId());
    }

    @Test
    public void deleteProductShouldSuccessDeleteTest() throws ServiceException {
        //when
        when(productService.getById(PRODUCT.getId())).thenReturn(PRODUCT);
        //then
        ResponseEntity<ProductDTO> result = productController.deleteProduct(PRODUCT.getId());
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(productService).deleteById(PRODUCT.getId());
    }
}
