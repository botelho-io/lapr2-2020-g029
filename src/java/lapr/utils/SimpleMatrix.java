package lapr.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple Matrix Class
 * <p>Representa uma matriz regular que pode aumentar em altura mas não em largura.</p>
 * @author 1150462 and 1192232
*/

public class SimpleMatrix<T> {
    List<T> matrixData; // Dados da matriz.
    int width;          // Largura da matriz (fixo).
    int height;         // Altura da matriz (inferido).

    /**
     * Criar uma matriz sem dados com uma certa largura.
     * @param w Largura da matriz.
     * @throws IllegalArgumentException Ver construtor completo.
     */
    public SimpleMatrix(int w) throws IllegalArgumentException{
        this(w, new ArrayList<T>());
    }

    /**
     * Cria uma nova matriz.
     * @param width Largura da matriz, caso a matriz esteja vazia pode ser 0 para ser inferido na primeira vez que uma linha for adicionada.
     * @param matrixData Dados da matriz.
     * @throws IllegalArgumentException Se a matriz não estiver vazia e a largura for 0 ou o tamanho da matriz não for múltiplo da largura.
     */
    public SimpleMatrix(int width, List<T> matrixData) throws IllegalArgumentException{
        this.matrixData = matrixData;
        if(!matrixData.isEmpty()) {
            if(width == 0) throw new IllegalArgumentException("Tamanho da matriz não pode ser automaticamente inferido de uma matriz não vazia");
            else {
                height = matrixData.size() / width;
                if(height*width != matrixData.size()) throw new IllegalArgumentException("Tamanho da matriz não é multiplo da sua largura");
            }
        } else {
            this.width = width;
            this.height = 0;
        }
    }

    /**
     * Adiciona uma linha, possivelmente incompleta, à matriz.
     * @param line Linha a adicionar.
     * @param padding Dados a colocar no final da matriz de modo a criar uma matriz completa.
     * @return Verdadeiro se pode adicionar uma nova linha à matriz; Falso caso contrário.
     * @throws IllegalArgumentException Ver (addLine).
     */
    public boolean addLine(List<T> line, T padding) throws IllegalArgumentException {
        if(!addLine(line)) return false;
        while(matrixData.size() != width*height) {
            matrixData.add(padding);
        }
        return true;
    }

    /**
     * Tenta adicionar uma linha à matriz.
     * @param line Linha a adicionar. (Se width == 0 este será inferido pelo tamanho de line)
     * @return Verdadeiro se foi possível adicionar à matriz.
     * @throws IllegalArgumentException If the line size is incorrect.
     */
    boolean addLine(List<T> line) throws IllegalArgumentException {
        if(line.size() > width) {
            if(width == 0) width = line.size();
            else throw new IllegalArgumentException("Tamanho da linha incompativél");
        }
        if (matrixData.addAll(line)) {
            this.height++;
            return true;
        }
        return false;
    }

    /**
     * Retorna um elemnto da matriz.
     * @param l Linha da matriz.
     * @param c Coluna da matriz.
     * @return O elemento na linha l e coluna c.
     */
    public T get(Integer l, Integer c) {
        return matrixData.get(width*l+c);
    }

    /**
     * Retorna uma lista inteira (não modificável) da matriz.
     * @param l Linha a retornar.
     * @return A linha l
     */
    public List<T> get(Integer l) {
        return Collections.unmodifiableList(matrixData.subList(width*l, width*(l+1)));
    }

    /**
     * Returns a altura da matriz.
     * @return  altura da matriz.
     */
    public Integer getHeight() {
        return height;
    }
}
