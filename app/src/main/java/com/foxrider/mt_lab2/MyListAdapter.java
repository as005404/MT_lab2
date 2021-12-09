package com.foxrider.mt_lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxrider.mt_lab2.model.Meme;
import com.squareup.picasso.Picasso;

import java.util.List;
// from  https://evileg.com/ru/post/232/ с моими доработками
public class MyListAdapter extends BaseAdapter {

    /*
     * Создаем объекты для отображения внешнего вида элемента
     * и объекта списка, с которым будет производиться работа
     */
    private LayoutInflater LInflater;
    private List<Meme> list;

    /*
     * Конструктор класса. В данном случае лишь транслируется лист с данными
     * в лист адаптера, с которым будет производиться непосредственная работа
     */
    public MyListAdapter(Context context, List<Meme> memes) {

        list = memes;
        LInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*
     * Далее идут стандартные методы родительского класса BaseAdapter.
     * Внимательно ознакомьтесь с отличиями методов в уроке и методов,
     * которые автоматически создает Android Studio.
     * Данные методы должны работать непосредственно с используемым нами ArrayList
     * и структурой данных, формируемой классом Meme
     */
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Meme getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * Метод, в котором формируется внешний вид элементов с его наполнением
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View v = convertView;

        /*
         * В том случае, если вид элемента не создан, производится его создание
         * с помощью ViewHolder и тегирование данного элемента конкретным holder объектом
         */
        if (v == null) {
            holder = new ViewHolder();
            v = LInflater.inflate(R.layout.list_item, parent, false);
            holder.memeImage = (ImageView) v.findViewById(R.id.memeImage);
            holder.name = (TextView) v.findViewById(R.id.name);
            v.setTag(holder);
        }

        /*
         * После того, как все элементы определены, производится соотнесение
         * внешнего вида, данных и конкретной позиции в ListView.
         * После чего из ArrayList забираются данные для элемента ListView и
         * передаются во внешний вид элемента
         */
        holder = (ViewHolder) v.getTag();
        Meme meme = getData(position);

        Picasso.with(v.getContext())
                .load(meme.getURL())
                .resize(140, 140)
                .into(holder.memeImage);
        holder.name.setText(meme.getName());

        return v;
    }

    /*
     * Метод, который забирает объект из ArrayList для дальнейшей работы с ним
     * и передачи его данных в элемент ListView
     */
    Meme getData(int position) {
        return (getItem(position));
    }

    /*
     * Данная структура данных необходима для того, чтобы при пролистывании
     * большого списка не возникало артефактов и перескакивания данных с одной позиции ListView
     * на другую, что достигается тегированием каждого элемента ListView
     */
    private static class ViewHolder {
        private ImageView memeImage;
        private TextView name;
    }
}