/*

@Component 
Mais genérico, pode ser usado em qualquer tipo de classe


@Service
Pode anotar a classe onde ficam as regras de negocio


@Repository
Classes onde vao acontecer persistencia de transacoes com o banco de dados


@Controller
Classes que vao receber algum tipo de requisicao, onde estao mapeadas as URLs que faremos as solicitacoes (GET,POST,PUT,DELETE)
A responsabilidade do controller é receber os dados e retornar ao usuario quando for necessario, olhar os dados que ele vai receber, fazer a validacao desses dados
O proprio spring recebe os dados em json e transforma em objetos no java
regras de interpretacao de dados e maneira de passa-los ficam no controller


@RestController
substitui o conjunto de @Controller e @ResponseBody



@Autowired
Cria pontos de injecao das classes onde foram utilizados Beans, ou seja, naquele ponto havera injecao das instancias das classes, onde o spring ira criar, manipular e destruir elementos daquela classe quando for preciso. Caso seja necessario utilizar alguma classe dentro de outra classe no spring ela sera incluida com o @Autowired



@Bean
Utilizado para mostrar ao spring que algum metodo de uma classe nao criada pelo usuario tambem precisa ser controlada pelo spring, tambem podendo criar pontos de injecao dessas em outras classes do programa



@RestController
facilita a criacao de APIs REST, aponta a classe como um bean do MVC, indica que suas respostas tem como body um JSON. o Proprio spring fará a conversao do objeto em questao para JSON na hora de devolver o request


Repositories
utilizam a notacao extends JpaRepository, o que desobriga a utilizacao de beans para indicar que ele também sera manipulado pelo spring.



@RequestMapping exemplo: @RequestMapping("/pessoa")
indica em qual endPoint os metodos da classe serao executados. um comando post ou get no caminho indicado chamara os metodos definidos em @PostMapping e @GetMapping respectivamente.



@SpringBootApplication
Define que aquela sera uma aplicacao spring boot e ja fara a importacao de bibliotecas importantes para o aplicativo


*/
