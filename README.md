# JPA
JPA con Spring MVC

Java annotation
---

Adiferencia de Hibernate se le agrega la anotacion @EnableJpaRepositories

###RootConfigContext
````java
@Configuration
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@EnableTransactionManagement(
        mode = AdviceMode.PROXY, proxyTargetClass = false,
        order = Ordered.LOWEST_PRECEDENCE
)
@EnableJpaRepositories(
        basePackages = "com.tomas.test",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean",
        transactionManagerRef = "jpaTransactionManager"
)
@ComponentScan(
        basePackages = {
                "com.tomas.test"},
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
````
####Java Beans

El bean Data source no cambia.

#####Config Data Base
````java
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        driverManagerDataSource.setUsername("tomas");
        driverManagerDataSource.setPassword("tomas");
        return driverManagerDataSource;
    }
````
#####Properties of Hibernate
Se elimina el metodo `properties()`
````java
    private Properties properties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }
````
#####Entity Manager 

En JPA no se usa Session se configura Entity Manager obtenido de `localContainerEntityManagerFactoryBean()`
. Si se obtiene la session apartir de `LocalSessionFactoryBean` se la saldra un Excepcion, este mismo Bean es usado
en la anotacion `@EnableJpaRepositories`.

````java
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean()
    {
        Map<String, Object> properties = new Hashtable<>();
        properties.put("javax.persistence.schema-generation.database.action",
                "none");

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");

        LocalContainerEntityManagerFactoryBean localSessionFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localSessionFactoryBean.setJpaVendorAdapter(adapter);
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(new String[]{"com.tomas.test"});
        localSessionFactoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        localSessionFactoryBean.setValidationMode(ValidationMode.NONE);
        localSessionFactoryBean.setJpaPropertyMap(properties);

        return localSessionFactoryBean;
    }
````
#####PlatformTransactionManager 
Al final se crea un bean para obtener la instacia de `PlatformTransactionManager`, este mismo bean es usado
en la anotacion `@EnableJpaRepositories`.
````java
    @Bean
    public PlatformTransactionManager jpaTransactionManager()
    {
        return new JpaTransactionManager(
                this.localContainerEntityManagerFactoryBean().getObject()
        );
    }
````

#####Services and Respository
```java
public interface SongsRepository extends CrudRepository<SongsEntity,Long>
{
} 
```

```java
    @Inject
    SongsRepository songsRepository;
```

```java
@Service
public class SongsServicesImplDao implements SongsServicesDao
{

    @Inject
    SongsRepository songsRepository;

    @Override
    @Transactional
    public void addPerson(SongsEntity songsEntity)
    {
        songsRepository.save(songsEntity);
    }
    /*
        ....
     */
````