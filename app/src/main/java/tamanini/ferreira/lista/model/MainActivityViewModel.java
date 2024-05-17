package tamanini.ferreira.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    //guarda a lista de itens cadastrados
    //método getter para obter a lista de itens
    List<MyItem> itens = new ArrayList<>();

    public List<MyItem> getItens() {
        return itens;
    }
}
