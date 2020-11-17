package controllers;

import com.griddynamics.controllers.ProductController;
import com.griddynamics.dto.ProductDTO;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    private static final ProductDTO PRODUCT = new ProductDTO(1, "testName", 100, "testDesc", "testBrand", "img.png");

    List<ProductDTO> products = Collections.singletonList(PRODUCT);

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void getAllProductsShouldReturnNullTest() {
        when(productService.findAll()).thenReturn(null);
        ResponseEntity<List<ProductDTO>> result = productController.getAllProducts();
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(result.getBody());
        verify(productService).findAll();
    }

    @Test
    public void getAllProductsShouldReturnProductsTest() {
        when(productService.findAll()).thenReturn(products);
        ResponseEntity<List<ProductDTO>> result = productController.getAllProducts();
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), products);
        verify(productService).findAll();
    }

    @Test
    public void getProductByIdShouldReturnsNullIdTest() {
        when(productService.getById(anyInt())).thenReturn(null);
        ResponseEntity<ProductDTO> result = productController.getProductById(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
    }

    @Test
    public void getProductByIdShouldReturnsNullProductTest() {
        when(productService.getById(anyInt())).thenReturn(null);
        ResponseEntity<ProductDTO> result = productController.getProductById(2);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(result.getBody());
    }

    @Test
    public void getProductByIdShouldReturnsProductTest() {
        when(productService.getById(PRODUCT.getId())).thenReturn(PRODUCT);
        ResponseEntity<ProductDTO> result = productController.getProductById(PRODUCT.getId());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), PRODUCT);
        verify(productService).getById(PRODUCT.getId());
    }

    @Test
    public void addNewProductShouldReturnNullTest() {
        ResponseEntity<ProductDTO> result = productController.addNewProduct(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
    }

    @Test
    public void addNewProductShouldReturnProductTest() {
        ResponseEntity<ProductDTO> result = productController.addNewProduct(PRODUCT);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertEquals(result.getBody(), products.get(0));
    }

    @Test
    public void editProductByIdShouldReturnNullTest() {
        ResponseEntity<ProductDTO> result = productController.editProductById(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
    }

    @Test
    public void editProductByIdShouldReturnProductTest() {
        ResponseEntity<ProductDTO> result = productController.editProductById(PRODUCT);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), products.get(0));
    }

    @Test
    public void deleteProductShouldReturnsNegativeTest() {
        when(productService.getById(PRODUCT.getId())).thenReturn(PRODUCT);
        ResponseEntity<ProductDTO> result = productController.deleteProduct(null);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(result.getBody());
    }

    @Test
    public void deleteProductShouldReturnsNullProductTest() {
        when(productService.getById(PRODUCT.getId())).thenReturn(PRODUCT);
        ResponseEntity<ProductDTO> result = productController.deleteProduct(2);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteProductShouldSuccessDeleteTest() {
        when(productService.getById(PRODUCT.getId())).thenReturn(PRODUCT);
        ResponseEntity<ProductDTO> result = productController.deleteProduct(PRODUCT.getId());
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
