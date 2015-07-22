package br.com.giftList.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import br.com.giftList.dao.PresenteDAO;

@Entity
public class Presente implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String descricao;
	private String ondeEncontrar;
	@Lob
	private byte[] imagem;
	private String nomeImagem;
	@ManyToOne 
	private Evento evento;
	private Date dtEvento;
	@ManyToOne
	private Usuario usuario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getOndeEncontrar() {
		return ondeEncontrar;
	}
	public void setOndeEncontrar(String ondeEncontrar) {
		this.ondeEncontrar = ondeEncontrar;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Date getDtEvento() {
		return dtEvento;
	}
	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public String getNomeImagem() {
		return nomeImagem;
	}
	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}
}
