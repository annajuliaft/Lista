package tamanini.ferreira.lista.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tamanini.ferreira.lista.R;
import tamanini.ferreira.lista.activity.MainActivity;
import tamanini.ferreira.lista.model.MyItem;

//responsavel por construir e preencher um item na lista
public class MyAdapter extends RecyclerView.Adapter {

    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem>itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }



    @NonNull
    @Override
    //criando os elementos de interface
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //obtendo um inflador, que sera usado para ler o arquivo xml
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //usando o inflador para criar os elementos de interface e guardamos dentro de um  objeto do tipo View
        View v = inflater.inflate(R.layout.item_list,parent,false);
        //guardado dentro de um objeto do tipo MyViewHolder
        return new MyViewHolder(v);
    }

    @Override
    //recebe o ViewHolder criado no metodo anterios e preenche os elementos de UI com os dados do item
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //obtemos o item que será usado para preencher a UI
        MyItem myItem = itens.get(position);
        //objeto do tipo View que está guardado dentro do ViewHolder
        View v = holder.itemView;
        //preenchendo a UI com os dados do item
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageBitmap (myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.description);
    }

    @Override
    //informando quantos elementos a lista possui
    public int getItemCount() {

        return itens.size();
    }


}
