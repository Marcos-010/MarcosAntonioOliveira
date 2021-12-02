package marcosantonio.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import marcosantonio.domain.Empresa;
import marcosantonio.service.Empresaservice;

@Component
public class StringToEmpresa implements Converter<String, Empresa>{

	@Autowired
	private Empresaservice service;
	
	@Override
	public Empresa convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}