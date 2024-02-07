package com.agendaspring.dominio.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

public class ESituacaoTarefaConverter implements AttributeConverter<ESituacaoTarefa, String> {

	@Override
	public String convertToDatabaseColumn(ESituacaoTarefa attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getDescricao();
	}

	@Override
	public ESituacaoTarefa convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		
		return Stream.of(ESituacaoTarefa.values())
				.filter(s -> s.getDescricao().equals(dbData))
				.findFirst()
				.orElse(null);
	}

}
