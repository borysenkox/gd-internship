package services;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Product;
import com.griddynamics.mappers.ProductMapper;
import com.griddynamics.repositories.ProductRepository;
import com.griddynamics.service.ProductService;
import com.griddynamics.validators.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private static Product PRODUCT;

    private static ProductDTO PRODUCT_DTO;

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
        PRODUCT = new Product(1, "Product", 1300., "description",
                "brand", "image", new ArrayList<>());

        PRODUCT_DTO = new ProductDTO(PRODUCT);
    }

    @Test
    public void findAll_DatabaseHasSomeItems_ShouldReturnListOfThreeEntities() {

        Iterable<Product> iterable;
        ArrayList<Product> arrList = new ArrayList<>();
        ArrayList<ProductDTO> arrayListDTO = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            Product product = new Product();
            ProductDTO productDTO = new ProductDTO();

            product.setId(i);
            productDTO.setId(i);

            arrList.add(product);
            arrayListDTO.add(productDTO);
        }

        iterable = arrList;


        Mockito.when(productRepository.findAll()).thenReturn(iterable);
        Mockito.when(productMapper.mapDTOList(iterable)).thenReturn(arrayListDTO);


        List<ProductDTO> result = productService.findAll();

        Assert.assertEquals(3, result.size());
    }

    @Test
    public void getById_IdIsPresentInDatabase_ShouldReturnExactEntity() {
        Integer id = 1;

        Optional<Product> optional = Optional.of(PRODUCT);

        Answer<ProductDTO> productDTOAnswer = invocationOnMock -> {
            ProductDTO productDTO1 = invocationOnMock.getArgument(1);
            Product product1 = invocationOnMock.getArgument(0);

            productDTO1.setId(product1.getId());
            productDTO1.setName(product1.getName());
            productDTO1.setPrice(product1.getPrice());
            productDTO1.setDescription(product1.getDescription());
            productDTO1.setBrand(product1.getBrand());
            productDTO1.setImage(product1.getImage());
            productDTO1.setCategoryDTOList(new ArrayList<>());

            return productDTO1;
        };


        Mockito.when(productRepository.findById(id)).thenReturn(optional);
        Mockito.when
                (productMapper.mapDTO(Mockito.any(Product.class), Mockito.any(ProductDTO.class)))
                .then(productDTOAnswer);


        ProductDTO result = productService.getById(id);

        Mockito.verify(validator).validateId(id);
        Assert.assertEquals(PRODUCT_DTO, result);
    }

    @Test
    public void getById_IdIsNotPresent_ShouldReturnNull() {
        Integer id = 2;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);

        Optional<Product> optional = Optional.empty();


        Mockito.when(productRepository.findById(id)).thenReturn(optional);


        ProductDTO result = productService.getById(id);

        Mockito.verify(validator).validateId(id);
        Assert.assertNull(result);
    }

    @Test
    public void save_ProductDTOIsValid_ShouldSaveEntityAtDataBase() {

        Product product = new Product(null, "Product", 1300., "description",
                "brand", "image", new ArrayList<>());

        ProductDTO productDTO = new ProductDTO(product);


        Mockito.when(productMapper.mapEntity(productDTO)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(PRODUCT);


        productService.save(productDTO);

        Mockito.verify(validator).validateDTO(productDTO);
        Mockito.verify(productMapper).mapEntity(productDTO);
        Mockito.verify(productRepository).save(product);
    }

    @Test
    public void update_ObjectIsPresentInTheDatabaseAndDataIsValid_ShouldReturnUpdatedObject() {
        Optional<Product> optional = Optional.of(PRODUCT);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(PRODUCT.getId());
        productDTO.setName("Another Name");

        Answer<Product> answer = invocation -> {
            PRODUCT.setName(productDTO.getName());

            return PRODUCT;
        };


        Mockito.when(productRepository.findById(productDTO.getId())).thenReturn(optional);
        Mockito.when(productMapper.mapUpdate(productDTO, PRODUCT)).then(answer);
        Mockito.when(productRepository.save(PRODUCT)).thenReturn(PRODUCT);


        productService.update(productDTO);

        Mockito.verify(validator).validateId(PRODUCT_DTO.getId());
        Mockito.verify(productRepository).findById(productDTO.getId());
        Mockito.verify(productMapper).mapUpdate(productDTO, PRODUCT);
        Mockito.verify(productRepository).save(PRODUCT);
        Mockito.verify(productMapper).mapDTO(PRODUCT, productDTO);
    }


    @Test(expected = NoSuchElementException.class)
    public void update_ObjectWithIDIsNotPresent_ShouldThrowNoSuchElementException() {
        Optional<Product> optional = Optional.empty();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(PRODUCT.getId());
        productDTO.setName("Another Name");


        Mockito.when(productRepository.findById(productDTO.getId())).thenReturn(optional);


        productService.update(productDTO);

        Mockito.verify(validator).validateId(PRODUCT_DTO.getId());
        Mockito.verify(productRepository).findById(productDTO.getId());
    }

    @Test
    public void deleteById_ObjectIsPresent_ShouldReturnNothing() {
        Integer id = 1;


        productService.deleteById(id);

        Mockito.verify(validator).validateId(id);
        Mockito.verify(productRepository).deleteById(id);
    }


}
