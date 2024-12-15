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


## Spring cloud Gateway


## Servidor de configuração centralizada. 

Quando um microsserviço é levantado, antes de se registrar no Eureka, ele busca as configurações no repositório central de configurações.

#### repositório git com as configurações 
https://github.com/FrankDestro/microservices-config

## Autenticação e Autorização 

![image](https://github.com/FrankDestro/Microservice-SpringBoot-Java/assets/93776452/d578932f-63e8-4f79-bbb9-91b50bbf5122)


