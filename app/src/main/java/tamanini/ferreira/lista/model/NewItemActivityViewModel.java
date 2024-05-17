package tamanini.ferreira.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
//garantir que a imagem escolhida pelo usuário continuará aparecendo
//mesmo que ele gire a tela enquanto NewItemActivity estiver funcionando
    Uri selectPhotoLocation = null;
//método getter para obter a lista de itens
    public Uri getSelectPhotoLocation() {

        return selectPhotoLocation;
    }
//método de setter para setar o
//endereço URI dentro do ViewModel
    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
