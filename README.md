# Projeto para Curso de Microservice Java com Spring Boot.

* Java 21 | Docker 24.0 | Spring boot 3.3.0
* WebClient | RestTemplate | OpenFeign 3.1.3 (Comnunicação APIs)| Spring LoaderBalance 3.1.3 (Balanciador de carga) | Eureka Server (Discovery)

### Modelo conceitual

![image](https://github.com/FrankDestro/Microservice-SpringBoot-Java/assets/93776452/7776b279-c7cc-4049-a54b-e1b1a35d61e2)

### Arquitetura de Microserviços para o Curso DevSuperior. 

![image](https://github.com/FrankDestro/Microservice-SpringBoot-Java/assets/93776452/1039ea3c-20a2-4319-8ef8-71c254a3b38c)

### Leitura. 

### Microservices - a definition of this new architectural term. 

* https://martinfowler.com/articles/microservices.html

* https://medium.com/introducao-a-arquitetura-de-microservicos/introdu%C3%A7%C3%A3o-a-microsservi%C3%A7os-25378269e6f9


## Open Feign 



## Spring Cloud LoaderBalance 



## Eureka Server 

O Eureka Server é uma parte fundamental da arquitetura de microservices, especialmente quando se utiliza o padrão de registro e descoberta de serviços. Ele é uma aplicação do Spring Cloud Netflix que atua como um servidor de registro de serviços. Em um ambiente de microservices, onde há muitos serviços sendo executados e se comunicando entre si, é importante ter uma maneira de descobrir dinamicamente onde esses serviços estão localizados na rede.

O Eureka Server permite que os serviços se registrem nele, informando seu nome e endereço de rede. Assim, outros serviços podem consultar o Eureka Server para descobrir onde um serviço específico está localizado. Isso é especialmente útil em ambientes em nuvem, onde os serviços podem ser escalados dinamicamente e podem ser movidos entre diferentes máquinas ou instâncias.

Em resumo, o Eureka Server fornece um registro centralizado de todos os serviços disponíveis na arquitetura de microservices e permite que outros serviços encontrem e se comuniquem com esses serviços de forma dinâmica e eficiente.
