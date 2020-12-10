/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podaalphabeta;
import static java.lang.Integer.MIN_VALUE;
import javax.swing.JOptionPane;
/**
 *
 * @author lalog
 */
public class PodaAlphaBeta {
    public static int fila_columna;
    public static boolean jugador;
    public static int matriz[][];
    public static int posicioni,posicionj;
    public static int alpha,beta;
    /**
     * @param args the command line arguments
     */
    public static void tiradaComputadora(){
        if(ganador()==-1 && verificarTablero()){
            int posicioni=0;
            int posicionj=0;
            int valor = Integer.MIN_VALUE;
            int aux;
            for (int i = 0;i < fila_columna; i++) {
                for (int j = 0; j < fila_columna; j++) {
                    if(matriz[i][j]==-1){
                        matriz[i][j]=1;
                        aux=minimizarBeta();
                        if(aux>valor){
                           valor=aux;
                           posicioni=i;
                           posicionj=j;
                        }
                        matriz[i][j]=-1;
                    }
                }
            }
            matriz[posicioni][posicionj]=1;
        }
    }
    
    public static void tiradaComputadora2(){
        if(ganador()==-1 && verificarTablero()){
            int posicioni=0;
            int posicionj=0;
            int valor = Integer.MIN_VALUE;
            int aux;
            for (int i = 0;i < fila_columna; i++) {
                for (int j = 0; j < fila_columna; j++) {
                    if(matriz[i][j]==-1){
                        matriz[i][j]=0;
                        aux=minimizarBeta2();
                        if(aux>valor){
                           valor=aux;
                           posicioni=i;
                           posicionj=j;
                        }
                        matriz[i][j]=-1;
                    }
                }
            }
            matriz[posicioni][posicionj]=0;
        }
    }
    
    public static int maximizarAlpha2(){
        if(ganador()==0 || ganador()==1 || !verificarTablero()){
            if(ganador()!=-1){
                return -1;
            }else{
                return 0;
            }
        }
        int valor = Integer.MIN_VALUE;
        for (int i = 0; i < fila_columna; i++) {
            for (int j = 0; j < fila_columna; j++) {
                if(matriz[i][j]==-1){
                    matriz[i][j]=0;
                    alpha=minimizarBeta2();
                    if(alpha>valor){
                        valor=alpha;
                    }
                    matriz[i][j]=-1;
                }
            }
        }
        return valor;
    }
    
     public static int minimizarBeta2(){
         if(ganador()==0 || ganador()==1 || !verificarTablero()){
            if(ganador()!=-1){
                return 1;
            }else{
                return 0;
            }
        }
        int valor = Integer.MAX_VALUE;
        for (int i = 0; i < fila_columna; i++) {
            for (int j = 0; j < fila_columna; j++) {
                if(matriz[i][j]==-1){
                    matriz[i][j]=1;
                    beta=maximizarAlpha2();
                    if(beta<valor){
                        valor=beta;
                    }
                    matriz[i][j]=-1;
                }
            }
        }
        return valor;
    }
    
    
    public static int maximizarAlpha(){
        if(ganador()==0 || ganador()==1 || !verificarTablero()){
            if(ganador()!=-1){
                return -1;
            }else{
                return 0;
            }
        }
        int valor = Integer.MIN_VALUE;
        for (int i = 0; i < fila_columna; i++) {
            for (int j = 0; j < fila_columna; j++) {
                if(matriz[i][j]==-1){
                    matriz[i][j]=1;
                    alpha=minimizarBeta();
                    if(alpha>valor){
                        valor=alpha;
                    }
                    matriz[i][j]=-1;
                }
            }
        }
        return valor;
    }
    
     public static int minimizarBeta(){
         if(ganador()==0 || ganador()==1 || !verificarTablero()){
            if(ganador()!=-1){
                return 1;
            }else{
                return 0;
            }
        }
        int valor = Integer.MAX_VALUE;
        for (int i = 0; i < fila_columna; i++) {
            for (int j = 0; j < fila_columna; j++) {
                if(matriz[i][j]==-1){
                    matriz[i][j]=0;
                    beta=maximizarAlpha();
                    if(beta<valor){
                        valor=beta;
                    }
                    matriz[i][j]=-1;
                }
            }
        }
        return valor;
    }
    
    
    public static void main(String[] args) {
        fila_columna=3;
        int jug;
        boolean aceptar=false;
        int op = 0;
        do {            
            try {
                 op =Integer.parseInt(JOptionPane.showInputDialog("1.-Jugador vs Jugador \n 2.-Jugador vs Maquina \n 3.-Maquina vs Maquina \nIngrese la opcione que desee"));
                 if(op>0 && op<4){
                     aceptar=true;
                 }else{
                     JOptionPane.showMessageDialog(null, "Opcion no valida");
                 }
            } catch (Exception e) {
            }
        } while (!aceptar);
        switch(op){
            case 1:
                jugador=true;
                matriz=new int[fila_columna][fila_columna];
                for (int i = 0; i < fila_columna; i++) {
                    for (int j = 0; j < fila_columna; j++) {
                        matriz[i][j]=-1;
                    }
                }
                while(true){
                    if(jugador){
                        tirar();
                    }else{
                        tirar2();
                        jugador=true;
                    }
                    mostrarTablero2();
                    if(ganador()==0){
                        System.out.println("Felicidades jugador 1 has ganado");
                        break;
                    }else if(ganador()==1){
                        System.out.println("Felicidades jugador 2 has ganado");
                        break;
                    }else if(ganador()==-1 && !verificarTablero()){
                        System.out.println("Empate");
                        break;
                    }
                }
                break;
            case 2:
                do {  
                    try {
                        jug=Integer.parseInt(JOptionPane.showInputDialog("Ingrese cual jugador quiere que inicie \n 1.-Humano \n 2.-Computadora"));
                    } catch (Exception e) {
                        jug=0;
                    }
                } while (jug<1 || jug>2);
                if(jug==1){
                    jugador=true;
                }else{
                    jugador=false;
                }
                matriz=new int[fila_columna][fila_columna];
                for (int i = 0; i < fila_columna; i++) {
                    for (int j = 0; j < fila_columna; j++) {
                        matriz[i][j]=-1;
                    }
                }
                while(1==1){
                    if(jugador){
                       tirar();
                    }else{
                        tiradaComputadora();
                        jugador=true;
                    }
                    mostrarTablero();
                    if(ganador()==0){
                        System.out.println("Felicidades Humano has ganado");
                        break;
                    }else if(ganador()==1){
                        System.out.println("La computadora ha ganado");
                        break;
                    }else if(ganador()==-1 && !verificarTablero()){
                        System.out.println("Empate");
                        break;
                    }
                } 
                break;
            case 3:
                matriz=new int[fila_columna][fila_columna];
                for (int i = 0; i < fila_columna; i++) {
                    for (int j = 0; j < fila_columna; j++) {
                        matriz[i][j]=-1;
                    }
                }
                int cont=0;
                while(1==1){
                    if(cont%2==0){
                        tiradaComputadora();
                        cont++;
                    }else{
                        tiradaComputadora2();
                        cont++;
                    }
                    mostrarTablero();
                    if(ganador()==0){
                        System.out.println("La computadora 1 ha ganado");
                        break;
                    }else if(ganador()==1){
                        System.out.println("La computadora 2 ha ganado");
                        break;
                    }else if(ganador()==-1 && !verificarTablero()){
                        System.out.println("Empate");
                        break;
                    }
                } 
                break;
        }
               
    }
    
    public static void tirar(){
        boolean valido=false;
        do {            
            try {
                posicioni=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila"));
                posicionj=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna"));
                if(posicioni>=0 && posicioni<3 && posicionj>=0 && posicionj<3){
                    if(matriz[posicioni][posicionj]==-1){
                        matriz[posicioni][posicionj]=0;
                        valido=true;
                    }else{
                        JOptionPane.showMessageDialog(null,"La posicion ya esta ocupada");
                    }
                }
            } catch (Exception e) {
                valido=false;
            }
        } while (!valido);
        jugador=false;
    }
    
      public static void tirar2(){
        boolean valido=false;
        do {            
            try {
                posicioni=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila"));
                posicionj=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna"));
                if(posicioni>=0 && posicioni<3 && posicionj>=0 && posicionj<3){
                    if(matriz[posicioni][posicionj]==-1){
                        matriz[posicioni][posicionj]=1;
                        valido=true;
                    }else{
                        JOptionPane.showMessageDialog(null,"La posicion ya esta ocupada");
                    }
                }
            } catch (Exception e) {
                valido=false;
            }
        } while (!valido);
        jugador=false;
    }
    
    public static int ganador(){
        if(matriz[0][0]==0 && matriz[1][1]==0 && matriz[2][2]==0){
            return 0;
        }else if(matriz[2][0]==0 && matriz[1][1]==0 && matriz[0][2]==0){
            return 0;
        }else if(matriz[0][0]==0 && matriz[0][1]==0 && matriz[0][2]==0){
            return 0;
        }else if(matriz[1][0]==0 && matriz[1][1]==0 && matriz[1][2]==0){
            return 0;
        }else if(matriz[2][0]==0 && matriz[2][1]==0 && matriz[2][2]==0){
            return 0;
        }else if(matriz[0][0]==0 && matriz[1][0]==0 && matriz[2][0]==0){
            return 0;
        }else if(matriz[0][1]==0 && matriz[1][1]==0 && matriz[2][1]==0){
            return 0;
        }else if(matriz[0][2]==0 && matriz[1][2]==0 && matriz[2][2]==0){
            return 0;
        }else if(matriz[0][0]==1 && matriz[1][1]==1 && matriz[2][2]==1){
            return 1;
        }else if(matriz[2][0]==1 && matriz[1][1]==1 && matriz[0][2]==1){
            return 1;
        }else if(matriz[0][0]==1 && matriz[0][1]==1 && matriz[0][2]==1){
            return 1;
        }else if(matriz[1][0]==1 && matriz[1][1]==1 && matriz[1][2]==1){
            return 1;
        }else if(matriz[2][0]==1 && matriz[2][1]==1 && matriz[2][2]==1){
            return 1;
        }else if(matriz[0][0]==1 && matriz[1][0]==1 && matriz[2][0]==1){
            return 1;
        }else if(matriz[0][1]==1 && matriz[1][1]==1 && matriz[2][1]==1){
            return 1;
        }else if(matriz[0][2]==1 && matriz[1][2]==1 && matriz[2][2]==1){
            return 1;
        }else{
            return -1;
        }
    }
    
    public static void mostrarTablero(){
        System.out.println("\n");
        if(!jugador){
            System.out.println("Movimiento Humano");
        }else{
            System.out.println("Movimiento computadora");
        }
        for (int i = 0; i < fila_columna; i++) {
            for (int j = 0; j < fila_columna; j++) {
                System.out.print(matriz[i][j]+" \t");
            }
            System.out.println("");
        }
    }
    
     public static void mostrarTablero2(){
        System.out.println("\n");
        if(!jugador){
            System.out.println("Movimiento Jugador 1");
        }else{
            System.out.println("Movimiento jugador 2");
        }
        for (int i = 0; i < fila_columna; i++) {
            for (int j = 0; j < fila_columna; j++) {
                System.out.print(matriz[i][j]+" \t");
            }
            System.out.println("");
        }
    }
    
    public static boolean verificarTablero(){
        for (int i = 0; i < fila_columna; i++) {
            for (int j = 0; j < fila_columna; j++) {
                if(matriz[i][j]==-1){
                    return true;
                }
            }
        }
        return false;
    }
}