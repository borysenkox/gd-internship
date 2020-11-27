package services;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Product;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.mappers.ProductMapper;
import com.griddynamics.repositories.ProductRepository;
import com.griddynamics.service.ProductService;
import com.griddynamics.validators.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static Product product;

    private static ProductDTO productDTO;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private Validator<ProductDTO> validator;

    @InjectMocks
    private ProductService productService;

    @Before
    public void beforeAllMethods() {
        product = new Product(1, "Product", 1300., "description",
                "brand", "image", new ArrayList<>());

        productDTO = new ProductDTO(product);
    }

    @Test
    public void findAll_DatabaseHasSomeItems_ShouldReturnListOfThreeEntities() {

        /* Given */
        List<Product> productList = Collections.singletonList(product);
        List<ProductDTO> expected = Collections.singletonList(productDTO);
        Iterable<Product> iterable = productList;

        /* When */
        Mockito.when(productRepository.findAll()).thenReturn(iterable);
        Mockito.when(productMapper.mapDTOList(iterable)).thenReturn(expected);

        /* Then */
        List<ProductDTO> actual = productService.findAll();

        Assert.assertEquals(expected, actual);
        Mockito.verify(productRepository).findAll();
        Mockito.verify(productMapper).mapDTOList(iterable);
    }

    @Test
    public void getById_IdIsPresentInDatabase_ShouldReturnExactEntity() throws ServiceException {

        /* Given */
        Integer id = 1;

        Optional<Product> optional = Optional.of(product);

        /* When */
        Mockito.when(productRepository.findById(id)).thenReturn(optional);
        Mockito.when(productMapper.mapDTO(product)).thenReturn(productDTO);

        /* Then */
        ProductDTO actual = productService.getById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(productRepository).findById(id);
        Mockito.verify(productMapper).mapDTO(product);
        Assert.assertEquals(productDTO, actual);
    }

    @Test
    public void getById_IdIsNotPresent_ShouldReturnNull() throws ServiceException {

        /* Given */

        Integer id = 2;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);

        Optional<Product> optional = Optional.empty();

        /* When */
        Mockito.when(productRepository.findById(id)).thenReturn(optional);

        /* Then */
        ProductDTO actual = productService.getById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(productRepository).findById(id);
        Mockito.verify(productMapper, Mockito.never()).mapDTO(Mockito.any(Product.class));
        Assert.assertNull(actual);
    }

    @Test
    public void save_ProductDTOIsValid_ShouldSaveEntityAtDataBase() throws ServiceException {

        /* Given */
        Product productWithoutID = new Product(null, "Product", 1300., "description",
                "brand", "image", new ArrayList<>());

        ProductDTO productDTO = new ProductDTO(productWithoutID);

        /* When */
        Mockito.when(productMapper.mapEntity(productDTO)).thenReturn(productWithoutID);
        Mockito.when(productRepository.save(productWithoutID)).thenReturn(product);

        /* Then */
        productService.save(productDTO);

        Mockito.verify(validator).validateDTO(productDTO);
        Mockito.verify(productMapper).mapEntity(productDTO);
        Mockito.verify(productRepository).save(productWithoutID);
    }

    @Test
    public void update_ObjectIsPresentInTheDatabaseAndDataIsValid_ShouldReturnUpdatedObject() throws ServiceException {

        /* Given */
        Optional<Product> optional = Optional.of(product);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName("Another Name");

        Answer<Product> answer = invocation -> {
            product.setName(productDTO.getName());

            return product;
        };

        /* When */
        Mockito.when(productRepository.findById(productDTO.getId())).thenReturn(optional);
        Mockito.when(productMapper.mapUpdate(productDTO, product)).then(answer);
        Mockito.when(productRepository.save(product)).thenReturn(product);

        /* Then */
        productService.update(productDTO);

        Mockito.verify(validator).validateId(ProductServiceTest.productDTO.getId());
        Mockito.verify(productRepository).findById(productDTO.getId());
        Mockito.verify(productMapper).mapUpdate(productDTO, product);
        Mockito.verify(productRepository).save(product);
        Mockito.verify(productMapper).mapDTO(product);
    }


    @Test(expected = ServiceException.class)
    public void update_ObjectWithIDIsNotPresent_ShouldThrowNoSuchElementException() throws ServiceException {

        /* Given */
        Optional<Product> optional = Optional.empty();

        ProductDTO argument = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName("Another Name");

        /* When */
        Mockito.when(productRepository.findById(argument.getId())).thenReturn(optional);

        /* Then */
        productService.update(productDTO);

        Mockito.verify(validator).validateId(ProductServiceTest.productDTO.getId());
        Mockito.verify(productRepository).findById(productDTO.getId());
        Mockito.verify(productMapper, Mockito.never()).mapUpdate(productDTO, Mockito.any(Product.class));
        Mockito.verify(productRepository, Mockito.never()).save(Mockito.any(Product.class));
        Mockito.verify(productMapper, Mockito.never()).mapDTO(Mockito.any(Product.class));
    }

    @Test
    public void deleteById_ObjectIsPresent_ShouldReturnNothing() throws ServiceException {

        /* Given */
        Integer id = 1;

        /* When */
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        /* Then */
        productService.deleteById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(productRepository).deleteById(id);
        Mockito.verify(productRepository).findById(id);

    }

    @Test(expected = ServiceException.class)
    public void deleteById_ObjectIsNotPresent_ShouldThrowServiceException() throws ServiceException {


        /* Given */
        Integer id = 2;

        /* When */
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        /* Then */
        productService.deleteById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(productRepository).findById(id);
        Mockito.verify(productRepository, Mockito.never()).deleteById(id);
    }


}
