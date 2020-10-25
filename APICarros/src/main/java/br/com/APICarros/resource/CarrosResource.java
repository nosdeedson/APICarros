package br.com.APICarros.resource;

import java.awt.PageAttributes.MediaType;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.APICarros.model.Carro;
import br.com.APICarros.repository.Carros;
import br.com.APICarros.service.NativeScriptService;



@RestController
@RequestMapping("/carros")
public class CarrosResource {
	
	@Autowired
	private Carros carros;
	
	@Autowired
	private NativeScriptService nss = new NativeScriptService();
	
	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public Carro save(@RequestBody Carro carro){
		return carros.save(carro);
	}
	
	@GetMapping
	public List<Carro> all(){
		return carros.findAll();
	}
	
	@GetMapping("/tipos")
	public List<Integer> allTypes() {
		List<Carro> cars = new ArrayList<Carro>();
		List<Integer> typeCars = new ArrayList<Integer>();
		cars = carros.findAll();
		for (Carro carro : cars) {
			typeCars.add(carro.getTipoVeiculo());
		}
		
		return typeCars;
	}
	
	// retorna os veículos de acordo com o tipoVeiculo
	@RequestMapping(method = RequestMethod.GET, value = "tipos/{tipoVeiculo}")
	public List<String> allBrands(@PathVariable int tipoVeiculo) {
		
		List<Carro> cars = new ArrayList<Carro>();
		List<String> brandCars = new ArrayList<String>();
		
		brandCars  = nss.tiposCarros("select c.marca from Carro c where c.tipoVeiculo= :tipoVeiculo" , tipoVeiculo);
		
		return brandCars ;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "tipos/{tipoVeiculo}/{marca}")
	public List<String> allModels(@PathVariable int tipoVeiculo, @PathVariable String marca) {
		
		List<Carro> cars = new ArrayList<Carro>();
		List<String> modelCars = new ArrayList<String>();
		
		String sql = "select modelo from Carro  where marca= :marca and tipoVeiculo= :tipoVeiculo";
		
		modelCars = nss.modelos(sql, tipoVeiculo, marca);
		
		return modelCars;
	}
	

	
	@RequestMapping(method = RequestMethod.GET, value = "tipos/{tipoVeiculo}/{marca}/anos")
	public List<Integer> allYears(@PathVariable int tipoVeiculo, @PathVariable String marca) {
				
		String sql = "select anoModelo from Carro  where marca= :marca and tipoVeiculo= :tipoVeiculo";
		List<Integer> yearsCars = nss.anos(sql, tipoVeiculo, marca);
		
		return yearsCars;
	}
	
	//retorna o veículo de acordo com os dados obtidos com os métodos acima
	@RequestMapping(method = RequestMethod.GET, value = "/tipos/{tipoVeiculo}/{marca}/{ano}")
	public BigDecimal valueCar( @PathVariable int tipoVeiculo, @PathVariable String marca, @PathVariable int ano) {
		
		String sql = "select valor from Carro  where marca= :marca and tipoVeiculo= :tipoVeiculo and anoModelo= :anoModelo";
		
		BigDecimal valor = nss.valorVeiculo(sql, tipoVeiculo, marca, ano);

		return valor;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/comparativo")
	public List<Carro> comparandoCarros(@RequestBody List<String> valores){
		
		int tipoVeiculo = Integer.parseInt(valores.get(0));
		String marca = valores.get(1);
		int anoModelo = Integer.parseInt(valores.get(2));
		BigDecimal valor = new BigDecimal(valores.get(3));
		
		
		String sql = "from Carro where anoModelo= :anoModelo and tipoVeiculo= :tipoVeiculo and "
				+ " marca= :marca";
		
		List<Carro> carros = nss.veiculos(sql, tipoVeiculo, marca, anoModelo);
		Carro maiorValor = null;
		Carro menorValor = null;
		for (Carro carro : carros) {
			if( carro.getValor().compareTo(valor) == 1 || carro.getValor().compareTo(valor) == 0 )
				maiorValor = carro;
			else if( carro.getValor().compareTo(valor) == -1 || carro.getValor().compareTo(valor) == 0)
				menorValor = carro;
		}
		carros= new ArrayList<Carro>();
		carros.add(maiorValor);
		carros.add(menorValor);
		return carros;
	}
	
	
	
}
