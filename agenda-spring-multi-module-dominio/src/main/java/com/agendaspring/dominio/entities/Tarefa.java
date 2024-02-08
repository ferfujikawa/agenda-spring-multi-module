package com.agendaspring.dominio.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;

import com.agendaspring.dominio.valueobjects.PrazoTarefa;
import com.agendaspring.dominio.valueobjects.SituacaoTarefa;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "visualizada")
    private Boolean visualizada;

    @Embedded
    private SituacaoTarefa situacao;

    @Embedded
    private PrazoTarefa prazo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarefa", fetch = FetchType.LAZY)
	private List<HistoricoTarefa> historicos = new ArrayList<HistoricoTarefa>();

    protected Tarefa() {}

    public Tarefa(String titulo, LocalDateTime prazo, String anotacao) {
		
        this.id = UUID.randomUUID();
        this.titulo = titulo;
		this.prazo = new PrazoTarefa(prazo);
		this.visualizada = false;
		dataCadastro = LocalDateTime.now();
		situacao = new SituacaoTarefa();

        historicos.add(new HistoricoTarefa(this, anotacao, "Tarefa criada"));
	}

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Boolean getVisualizada() {
        return visualizada;
    }

    public SituacaoTarefa getSituacao() {
        return situacao;
    }

    public PrazoTarefa getPrazo() {
        return prazo;
    }

    public Boolean podeMarcarComoVisualizada() {
        return !this.visualizada;
    }

    public Boolean podeMarcarComoNaoVisuazalida() {
        return this.situacao.podeMarcarNaoVisualizada() && visualizada;
    }

    public Boolean podeDarAndamento() {
        return this.situacao.podeDarAndamento();
    }

    public Boolean podeCancelar() {
        return this.situacao.podeCancelar();
    }

    public Boolean marcarComoVisualizada(String anotacao) {
		if (this.podeMarcarComoVisualizada()) {
            historicos.add(new HistoricoTarefa(this, anotacao, "Tarefa marcada como visualizada"));
			visualizada = true;
			return true;
		}
		
		return false;
	}

	public Boolean marcarComoNaoVisualizada(String anotacao) {
		if (this.podeMarcarComoNaoVisuazalida()) {
            historicos.add(new HistoricoTarefa(this, anotacao, "Tarefa marcada como não visualizada"));
			visualizada = false;
			return true;
		}

		return false;
	}

    public Boolean registrarAnotacao(String anotacao) {
		historicos.add(new HistoricoTarefa(this, anotacao, "Inclusão de anotação à tarefa"));
		visualizada = true;
		return true;
	}

    public Boolean darAndamento(String anotacao) {
		if (this.podeDarAndamento()) {
            if (this.situacao.darAndamento()) {
                historicos.add(new HistoricoTarefa(this, anotacao, "Tarefa alterada para em andamento"));
                visualizada = true;
                return true;
            }
		}

		return false;
	}

    public Boolean cancelar(String anotacao) {
		if (this.podeCancelar()) {
            if (this.situacao.cancelar()) {
                historicos.add(new HistoricoTarefa(this, anotacao, "Tarefa cancelada"));
			    visualizada = true;
			    return true;
            }
        }

		return false;
	}
}
