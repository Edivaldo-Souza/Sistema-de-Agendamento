package com.cc.ed1.SistemaDeAgendamento.domain.entities;

public class Fila<T>{
	private int max;
    private Object[] array;
    private int primeiro;
    private int ultimo;

    Fila(int max){
        this.max = max;
        this.array = new Object[max];
        this.primeiro = -1;
        this.ultimo = -1;
    }

    public void adicionar(T obj){
        if(!isFull()){
        	ultimo++;
            this.array[ultimo] = obj;

            if(primeiro<0){
                primeiro = 0;
            }
        }
        
    }

    @SuppressWarnings("unchecked")
    public T remover(){
        if(primeiro>0){
        	T first = (T) this.array[primeiro];

            this.array[primeiro] = null;

            for(int i = ultimo; i>=0; i--){
                if(((ultimo-i)+1)>=max || this.array[(ultimo-i)+1]==null){
                    this.array[(ultimo-i)] = null;
                    ultimo = (ultimo-i)-1;
                    break;
                }
                this.array[ultimo-i] = this.array[(ultimo-i)+1];
            }

            return first;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if(!isEmpty()){
        	return (T) this.array[primeiro];   
        }
        return null;
    }

    public boolean isEmpty(){
        if(primeiro<0){
            return true;
        }
        else return true;
    }

    public boolean isFull(){
        if(ultimo==max-1){
            return true;
        }
        else return false;
    }

}
