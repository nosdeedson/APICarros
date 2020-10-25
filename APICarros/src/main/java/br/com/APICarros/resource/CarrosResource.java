package br.com.APICarros.resource;

import java.awt.PageAttributes.MediaType;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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



@RestController
@RequestMapping("/carros")
public class CarrosResource {
	
	@Autowired
	private Carros carros;
	
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
		
		cars = carros.findAll();
		
		for (Carro carro : cars) {
			if( carro.getTipoVeiculo() == tipoVeiculo)
				brandCars.add(carro.getMarca());
		}
		
		return brandCars;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "tipos/{tipoVeiculo}/{marca}")
	public List<String> allModels(@PathVariable int tipoVeiculo, @PathVariable String marca) {
		
		List<Carro> cars = new ArrayList<Carro>();
		List<String> modelCars = new ArrayList<String>();
		
		cars = carros.findAll();
		
		for (Carro carro : cars) {
			if ( carro.getMarca().equals(marca) && carro.getTipoVeiculo() == tipoVeiculo) {
				
				modelCars.add(carro.getModelo());
			}
		}
		
		return modelCars;
	}
	

	
	@RequestMapping(method = RequestMethod.GET, value = "tipos/{tipoVeiculo}/{marca}/anos")
	public List<Integer> allYears(@PathVariable int tipoVeiculo, @PathVariable String marca) {
		
		List<Carro> cars = new ArrayList<Carro>();
		List<Integer> yearsCars = new ArrayList<Integer>();
		cars = carros.findAll();
		for (Carro carro : cars) {
			if ( carro.getMarca().equals(marca) && carro.getTipoVeiculo() == tipoVeiculo)
				yearsCars.add(carro.getAnoModelo());
		}
		
		return yearsCars;
	}
	
	//retorna o veículo de acordo com os dados obtidos com os métodos acima
	@RequestMapping(method = RequestMethod.GET, value = "/tipos/{tipoVeiculo}/{marca}/{ano}")
	public ResponseEntity<BigDecimal> valueCar( @PathVariable int tipoVeiculo, @PathVariable String marca, @PathVariable int ano) {
		
		List<Carro> cars = carros.findAll();
		Carro retorno = new Carro();
		for (Carro carro : cars) {
			if ( carro.getTipoVeiculo() == tipoVeiculo && carro.getMarca().equals(marca) && carro.getAnoModelo() == ano)
			{
				retorno = carro ;
			}
		}
		return ResponseEntity.ok(retorno.getValor());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/comparativo")
	public List<Carro> comparandoCarros(@RequestBody List<String> valores){
		
		
		List<Carro> cars = carros.findAll();
		Carro maiorValor = null;
		Carro menorValor = null;
		
		BigDecimal valor = new BigDecimal(valores.get(3));
		
		for (Carro carro2 : cars) {
			
			if( carro2.getTipoVeiculo() == Integer.parseInt(valores.get(0)) && carro2.getMarca().equals(valores.get(1)) &&
					carro2.getAnoModelo() == Integer.parseInt(valores.get(2))  )
			{
				if( carro2.getValor().compareTo(valor) == 1 || carro2.getValor().compareTo(valor) == 0 ) {
					maiorValor = carro2;
				}
				else if( carro2.getValor().compareTo(valor) == -1 || carro2.getValor().compareTo(valor) == 0   )
					menorValor = carro2;
			}
			
		}
		cars.removeAll(cars);
		cars.add(maiorValor);
		cars.add(menorValor);
		return cars;
	}
	
	
	
}
