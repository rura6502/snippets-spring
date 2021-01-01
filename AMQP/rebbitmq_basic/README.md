# Spring AMQP simple example using RabbitMQ

 * used
   * spring-amqp
   * testcontainers : RabbitMQ

 simple project based on first tutorial in rabbit mq homepage.

 * class
   * RabbitMQTestContainerConfig.java : set testcontainers, set beans related spring-amqp.
     * `AmqpAdmin` : `Queue` use this bean to bind