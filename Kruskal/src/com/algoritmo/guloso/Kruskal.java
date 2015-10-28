package com.algoritmo.guloso;

import java.util.ArrayList;

//@author: Caio Vinicius Carvalho de Cerqueira

/*
Olá professor, segue o meu algoritmo de Kruskal de arvore geradora mínima.
Tem uma foto do grafo na pasta deste projeto para poder demonstrar melhor
como tudo foi feito.
*/
public class Kruskal {

	//esta matriz é o grafo conexo.
	private int M[][] = {{0,2,2,0},
						 {2,0,1,5},
						 {2,1,0,3},
						 {0,5,3,0}};
	//esta é a matriz que irá se tornar o novo grafo
	private int G[][] = new int[M.length][M.length];
	
	//Irei ordenar as verticies do menor para o maior nesta lista do tipo arrayList que armazena objetos
	private ArrayList<ArestaPeso> listaArestas = new ArrayList<>();
	 
	public Kruskal(){
		
		int valor = menorValorAresta();
		
		while(!(valor == 999)){
			for(int l = 0; l < M.length;l++){
				for(int c = 0; c < M.length;c++){
					if((M[l][c] == valor)&&(valor !=0)&&(valor <=999)){
						ArestaPeso p = new ArestaPeso();
						p.setDestino(c);
						p.setOrigem(l);
						p.setPeso(valor);
						this.listaArestas.add(p);
					}
				}
			}
			valor = proximoMenorValor(valor);
		}
		gerarMatrizKruskal();
	}
	
	//Este método vai buscando próximos valores de forma ordenada com base nos valores anteriores que são passados por parâmetro
	private int proximoMenorValor(int valorAnterior){
		int valor = 999;
		for(int l = 0; l < M.length;l++){
			for(int c = 0; c < M.length;c++){
				if((M[l][c] > valorAnterior)&&(M[l][c]!=0)&&(M[l][c] < valor)){
					valor = M[l][c];
				}
			}
		}
		return valor;
	}
	
	//Este método vai buscar a aresta com o menor valor, para poder iniciar os processos no construtor
	private int menorValorAresta(){
		int valor = 99999999;
		
		for(int l = 0; l < M.length;l++){
			for(int c = 0; c < M.length;c++){
				if((M[l][c] < valor)&&(M[l][c]!=0)){
					valor = M[l][c];
				}
			}
		}
		return valor;
	}
	
	private void gerarMatrizKruskal(){
		int tamanho = 0;
		
		for(int i = 0; i < this.listaArestas.size(); i++){
			if((!(verificaLaco(this.listaArestas.get(i).getOrigem(),this.listaArestas.get(i).getDestino()))&&(tamanho < G.length))){
				this.G[this.listaArestas.get(i).getOrigem()][this.listaArestas.get(i).getDestino()]= this.listaArestas.get(i).getPeso();
				this.G[this.listaArestas.get(i).getDestino()][this.listaArestas.get(i).getOrigem()]= this.listaArestas.get(i).getPeso();
				tamanho++;
			}
		}
	}
	
	private boolean verificaLaco(int origem,int destino){
		int pilha[] = new int[G.length];
		int cont= 0;
		
		for(int c = 0; c < G.length;c++){
			if(G[destino][c]!=0){
				pilha[cont] = G[destino][c];
				cont++;
			}
		}
		if(cont == 0){
			return false;
		}
		else{
			for(int i = 0; i < pilha.length; i++){
				for(int c = 0; c < G.length;c++){
					if(G[pilha[i]][c] == origem){
						return true;
					}
				}	
			}
					
		}
		return false;
	}
	
	//Este método lista a matriz que é usada como entrada.
	public void ListMatriz(){
		for(int l = 0; l < M.length;l++){
			for(int c = 0; c < M.length;c++){
				System.out.print(" "+M[l][c]);
			}
			System.out.println("");
		}
	}
	
	//este método lista a matriz gerada, o novo grafo.
	public void ListaGrafoGerado(){
		for(int l = 0; l < M.length;l++){
			for(int c = 0; c < M.length;c++){
				System.out.print(" "+G[l][c]);
			}
			System.out.println("");
		}
	}
	
	//Este método serve apenas para listar o arraylist que é uma lista de objetos de arestas.
	//com o intuito de debugar o código.
	public void listarLista(){
		for(int i = 0; i< this.listaArestas.size(); i++){
			System.out.println("===========================================");
			System.out.println("Indicie "+i+": ");
			System.out.println("Origem: "+this.listaArestas.get(i).getOrigem());
			System.out.println("Destino: "+this.listaArestas.get(i).getDestino());
			System.out.println("Peso: "+this.listaArestas.get(i).getPeso());
			System.out.println("===========================================");
		}
	}
	
	
	public static void main(String[] args) {
		//A maior parte da regra de négocios começa no construtor da classe, de lá, o sistema chama um método que gera o grafo.
		Kruskal k = new Kruskal();
		//aqui apenas listamos o grafo gerado.
		k.ListaGrafoGerado();
	}

}
