package rocks.gebsattel.hochzeit

import org.apache.catalina.core.ApplicationContext
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class HochzeitApplication {

	static void main(String[] args) {
        SpringApplication.run HochzeitApplication, args

        /* ApplicationContext ctx = SpringApplication.run(HochzeitApplication.class, args)

		println("Beans *************")
		println(ctx.getBeanDefinitionCount())
		for (name  in ctx.getBeanDefinitionNames()) {
            println(name)
        }
        */
	}
}
