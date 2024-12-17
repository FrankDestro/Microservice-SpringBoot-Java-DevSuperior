# Projeto para Curso de Microservice Java com Spring Boot.

* Java 21 
*  Docker 24.0 
*  Spring boot 3.3.0
*  WebClient | RestTemplate | OpenFeign 3.1.3 (Comnunicação APIs)
*  Spring LoaderBalance 3.1.3 (Balanciador de carga) 
*  Eureka Server (Discovery) 4.1.2
*  CircuitBreaker - resilience4j
*  sfj4j - Log 
*  Spring Cloug Gateway 4.1.2
*  Observability (Prometheus / Grafana)
*  Kafka (Message Broken)

## Fases para implementação 
* 1- Criação dos microserviços e integração através do OpenFeign.
* 2- Criação do server Eureka, implementação do Resiliense4j para tolerância a falhas e SPring Cloud API Gateway
* 3- Implementação de um server-config 
* 4- Autenticação e Autorização - OAuth2

### Modelo conceitual

![image](https://github.com/FrankDestro/Microservice-SpringBoot-Java/assets/93776452/7776b279-c7cc-4049-a54b-e1b1a35d61e2)

### Arquitetura de Microserviços para o Curso DevSuperior. 

![microservice-arquitetura](https://github.com/FrankDestro/Imagens-Readme/blob/main/microservice_devsuperior.drawio.png)

### Microservices 
* Eureka discovery - localhost:8761
* api-gateway-spring-cloud - localhost:9090
* hr-oauth - localhost:8083
* hr-payroll - localhost:random port
* hr-user - localhost:random port
* hr-worker - localhost:random port
* hr-email - localhost: random port

### Container
* kafka - localhost:9092 internal , external localhost:9093
* zookeeper - localhost:2181
* kafka-ui - localhost:8080
* redis - localhost:6379
* redis insight - localhost:5540
* prometheus - localhost:9091
* grafana- localhost:3000


### Leitura. 

### Microservices - a definition of this new architectural term. 

* https://martinfowler.com/articles/microservices.html

* https://medium.com/introducao-a-arquitetura-de-microservicos/introdu%C3%A7%C3%A3o-a-microsservi%C3%A7os-25378269e6f9


## Open Feign 

Realizar a comunicação entre API´s 

```js
@Component
@FeignClient(name = "hr-worker",
        path = "/workers")
public interface WorkerFeignClient {

    @GetMapping(value = "/{id}")
    ResponseEntity<Worker> findById(@PathVariable Long id);

}
```

## Spring Cloud LoadBalance 

O spring-cloud-starter-loadbalancer é uma biblioteca do Spring Cloud que fornece funcionalidades para realizar balanceamento de carga de forma programática. 

Ele oferece uma maneira fácil de integrar balanceamento de carga em aplicativos Spring que precisam se comunicar com serviços distribuídos

## Eureka Server 

O Eureka Server é uma parte fundamental da arquitetura de microservices, especialmente quando se utiliza o padrão de registro e descoberta de serviços. Ele é uma aplicação do Spring Cloud Netflix que atua como um servidor de registro de serviços. Em um ambiente de microservices, onde há muitos serviços sendo executados e se comunicando entre si, é importante ter uma maneira de descobrir dinamicamente onde esses serviços estão localizados na rede.

O Eureka Server permite que os serviços se registrem nele, informando seu nome e endereço de rede. Assim, outros serviços podem consultar o Eureka Server para descobrir onde um serviço específico está localizado. Isso é especialmente útil em ambientes em nuvem, onde os serviços podem ser escalados dinamicamente e podem ser movidos entre diferentes máquinas ou instâncias.

Em resumo, o Eureka Server fornece um registro centralizado de todos os serviços disponíveis na arquitetura de microservices e permite que outros serviços encontrem e se comuniquem com esses serviços de forma dinâmica e eficiente.

## CircuitBreaker by resiliense4j. 

O Circuit Breaker é um padrão de design usado em sistemas distribuídos, como microservices, para aumentar a resiliência e a estabilidade dos sistemas. Ele atua como um "disjuntor" em chamadas de serviços externos (APIs, bancos de dados, etc.), interrompendo temporariamente as tentativas de conexão a um serviço que está com falhas repetidas.

Como funciona:
Closed (Fechado): No estado inicial, o circuito está "fechado", ou seja, as chamadas de serviço ocorrem normalmente. Se as chamadas falham repetidamente, o Circuit Breaker muda para o estado "Open".

Open (Aberto): No estado "aberto", as chamadas são bloqueadas imediatamente, sem tentar acessar o serviço problemático. Esse estado permanece por um tempo configurado (timeout), após o qual o Circuit Breaker muda para o estado "Half-Open".

Half-Open (Meio-Aberto): Neste estado, o Circuit Breaker permite que algumas chamadas passem para testar se o serviço voltou a funcionar. Se essas chamadas forem bem-sucedidas, o Circuit Breaker volta ao estado "Closed". Se falharem, retorna ao estado "Open".

![image](https://github.com/user-attachments/assets/ddf978dd-91d0-486a-8dc5-5f982cc65569)

references : https://digitalvarys.com/what-is-circuit-breaker-design-pattern/ 
              https://resilience4j.readme.io/docs/circuitbreaker

### Circuit Breaker - @Retry

A anotação @Retry é usada para definir uma política de tentativa automática quando um método falha devido a uma exceção.
Basicamente, ela permite que o método seja reexecutado um número específico de vezes antes de falhar definitivamente. Se todas as tentativas falharem, um método de fallback pode ser chamado.

```java
@GetMapping("/foo-bar")
    @Retry(name = "foo-bar")
    public String fooBar() {
       looger.info("Request to foo-bar is received!");
       var response =  new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
       return response.getBody();
    }
```

application.yml

```yml
resilience4j:
  retry:
    instances:
      foo-bar:
        maxAttempts: 5
      default:
        maxAttempts: 5
        waitDuration: 1s
        enableExponentialBackoff: true
```
### Circuit Breaker - FallbackMethod 

Defini um método alternativo caso a falha venha a ocorrer. Deve-ser adicionar uma exception como argumento no fallbackMethod

```java
 @GetMapping("/foo-bar")
    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
    public String fooBar() {
       looger.info("Request to foo-bar is received!");
       var response =  new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
       return response.getBody();
    }

    public String fallbackMethod(Exception e) {
        return "fallbackMethod foo-bar!!!";
    }
```

### CircuitBreaker - @CircuitBreaker

A anotação @CircuitBreaker implementa o padrão de design "Circuit Breaker". Um Circuit Breaker monitora as falhas em um método e pode abrir o circuito para impedir chamadas subsequentes ao método quando muitas falhas ocorrem em um curto período de tempo. Enquanto o circuito está aberto, as chamadas ao método falham automaticamente, e um método de fallback pode ser chamado. Depois de um tempo, o circuito pode ser fechado novamente, permitindo que novas chamadas ao método sejam feitas.

```java
    @GetMapping("/foo-bar")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    public String fooBar() {
       looger.info("Request to foo-bar is received!");
       var response =  new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
       return response.getBody();
    }

    public String fallbackMethod(Exception e) {
        return "fallbackMethod foo-bar!!!";
    }
}

```

### Diferenças Principais: @Retry / @CircuitBreaker
* @Retry: Foca em reexecutar o método várias vezes antes de considerar que ele falhou definitivamente.

* @CircuitBreaker: Monitora falhas para abrir o circuito após muitas falhas seguidas, evitando novas chamadas ao método até que ele "feche" novamente.
Ambas as anotações podem ser usadas juntas, dependendo do cenário. Por exemplo, você pode querer várias tentativas (@Retry) antes de o circuito abrir (@CircuitBreaker).

### CircuitBreaker - @RateLimiter

O RateLimiter é usado para limitar o número de chamadas que podem ser feitas a um serviço em um determinado período de tempo, protegendo contra sobrecargas e garantindo que o serviço não seja bombardeado com requisições.
Isso é útil quando se quer controlar o fluxo de requisições para evitar atingir limites de API ou proteger recursos internos.

```ruby
    @GetMapping("/foo-bar")
    @RateLimiter(name = "foo-bar-rate")
    public String fooBar() {
       looger.info("Request to foo-bar is received!");
        return "Foo-Bar!!!";
    }
```

### CircuitBreaker - @Bulkhead

O Bulkhead permite que você limite o número de chamadas simultâneas a um serviço, criando "compartimentos" que isolam falhas e impedem que um subsistema sobrecarregado afete todo o sistema.
É especialmente útil em microserviços onde você deseja evitar que um serviço monopolize todos os recursos.

```ruby
    @GetMapping("/foo-bar")
    @Bulkhead(name = "foo-bar-bk")
    public String fooBar() {
       looger.info("Request to foo-bar is received!");
        return "Foo-Bar!!!";
    }
```

## Servidor de configuração centralizada. 

Quando um microsserviço é levantado, antes de se registrar no Eureka, ele busca as configurações no repositório central de configurações.

#### repositório git com as configurações 
https://github.com/FrankDestro/microservices-config

## Autenticação e Autorização 

![image](https://github.com/FrankDestro/Microservice-SpringBoot-Java/assets/93776452/d578932f-63e8-4f79-bbb9-91b50bbf5122)


## kafka Message Broken


