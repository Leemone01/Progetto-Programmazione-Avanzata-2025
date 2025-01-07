package it.unipi.MyAgendaServer;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class MyAgendaServerApplicationTests {
        @Autowired
        private Database database;
        
	@Test
	void contextLoads() {
	}
        
        //Verifico che il database venga effettivamente creato
        @Test
        void testDatabase(){
            try{
                //Verifico che il database '615600' sia presente
                JdbcTemplate jdbcTemplate = new JdbcTemplate(database.getDataSource());
                String query = "SELECT 1 FROM information_schema.schemata WHERE schema_name = '615600'";
                jdbcTemplate.queryForObject(query, Integer.class);
            }
            catch(DataAccessException ex){
                //In caso di errore (il database non c'è, non si ha un unico DB chiamato '615600' o ci sono stati altri errori)
                fail("Errore nella creazione del database: " + ex.getMessage());
            }
        }
}
