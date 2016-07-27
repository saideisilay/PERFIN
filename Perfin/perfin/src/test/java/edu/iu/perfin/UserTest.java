package edu.iu.perfin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.iu.perfin.config.PerfinApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PerfinApplication.class)
@WebAppConfiguration
public class UserTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * TODO : Bu test methodunun amaci kisi kaydetme ozelliginin dogru calisdigini incelemektir. 
     * Buna gore asagidaki islemleri gerceklestiriniz.
     * 
     * 1) Oncelikle bir Kisi objesi olusturun. Ve bu objeyi istediginiz gibi set edin. (new Kisi())
     * 2) Olusturdugunuz Kisi objesini JSONUtil sinifi ile String'e donusturebilirsiniz.
     * 3) /kisi/ url'ine kisi string'inizi content type json olacak sekilde post edin. Bu sayede Kisi kaydiniz veritabaninda olusacaktir.
     * 4) 3. adimda size donus degeri olarak (response) kaydedilen kisi stringi gelecektir. Bunu JSONUtil ile Kisi Objesine donusturebilirsiniz.
     * 5) Elde ettiginiz Kisi objesinin id alani set edilmis mi diye kontrol edin. (id alani dolu gelmesi veritabaninda olstugunu gosterir.)
     * */
    @Test
    public void birKisiKaydiOlusturanVeBuKaydinTablodaOlusmusOldugunuDogrulayanTest() throws Exception {
    	 //FIXME
    }


}