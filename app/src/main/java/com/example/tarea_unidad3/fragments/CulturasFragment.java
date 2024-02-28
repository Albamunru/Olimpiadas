package com.example.tarea_unidad3.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tarea_unidad3.MainActivity;
import com.example.tarea_unidad3.MyRecyclerViewAdapter;
import com.example.tarea_unidad3.Pais;
import com.example.tarea_unidad3.R;

import java.util.ArrayList;


public class CulturasFragment extends Fragment {


    Pais pais;
    ImageButton btn;
    int seleccionado ;
    MyRecyclerViewAdapter adapter;

    public CulturasFragment() {
        // Required empty public constructor
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] informaciones = obtenerInformaciones();
        String[] idiomas = obtenerIdiomas();

        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(new Pais("Uruguay",R.drawable.uruguay, 2, informaciones[0], idiomas[0]));
        paises.add(new Pais("Nicaragua",R.drawable.nicaragua,3, informaciones[1], idiomas[1]));
        paises.add(new Pais("Argentina",R.drawable.argentina,1, informaciones[2], idiomas[2]));
        paises.add(new Pais("Ucrania",R.drawable.ucrania,1, informaciones[3], idiomas[3]));
        paises.add(new Pais("Portugal", R.drawable.portugal,3, informaciones[4], idiomas[4]));
        paises.add(new Pais("Rumania",R.drawable.rumania,11, informaciones[5], idiomas[5]));
        paises.add(new Pais("Marruecos",R.drawable.marruecos,13, informaciones[6], idiomas[6]));
        paises.add(new Pais("Colombia",R.drawable.colombia,1, informaciones[7], idiomas[7]));
        paises.add(new Pais("Venezuela",R.drawable.vnezuela,4, informaciones[8], idiomas[8]));
        paises.add(new Pais("Honduras",R.drawable.honduras,3, informaciones[9], idiomas[9]));
        paises.add(new Pais("Italia",R.drawable.italia,1, informaciones[10], idiomas[10]));
        paises.add(new Pais("China",R.drawable.china,1, informaciones[11], idiomas[11]));
        paises.add(new Pais("Bolivia",R.drawable.bolivia,1, informaciones[12], idiomas[12]));
        paises.add(new Pais("Brasil",R.drawable.brasil,1, informaciones[13], idiomas[13]));

        RecyclerView rv = view.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), paises);
        adapter.setOnClickListener(new MyRecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                seleccionado = position;
                registerForContextMenu(view);
                obtenerPais(position);
            }
        });
        rv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                layoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.culturas_fragment, container, false);
    }

    private void obtenerPais(int position) {
        this.pais = this.adapter.getItem(position);
    }

    private String[] obtenerInformaciones() {
        String[] informaciones = {"Uruguay, aunque es uno de los países más pequeños de América del " +
                "Sur, destaca por su estabilidad política, calidad de vida y belleza natural. La " +
                "ubicación geográfica de Uruguay ha influido en su desarrollo económico y en su estilo " +
                "de vida tranquilo.","La ubicación geográfica de Nicaragua, con su diversidad de paisajes" +
                " y sus costas en dos océanos, ha influido en su ecología y en la vida de sus habitantes. " +
                "Nicaragua es conocida por su belleza natural, sus sitios históricos y su cultura vibrante.",
                "La ubicación geográfica de Argentina ha influido en su agricultura, economía y cultura. El " +
                        "país es conocido por su diversidad geográfica, desde las majestuosas montañas de los " +
                        "Andes hasta las extensas llanuras de la Pampa y las ciudades vibrantes como Buenos Aires."
                ,"La ubicación geográfica de Ucrania ha influido en su historia, economía y cultura. " +
                "El país ha experimentado momentos de cambio significativo a lo largo de los años, " +
                "incluyendo su independencia en 1991 tras la disolución de la Unión Soviética."
                ,"La situación geográfica de Portugal le otorga una variedad de paisajes, desde montañas " +
                "y colinas en el interior hasta hermosas playas a lo largo de la costa. Además, el clima " +
                "varía desde mediterráneo en el sur hasta atlántico en el norte.", "La ubicación " +
                "geográfica de Rumanía ha influido en su historia y desarrollo, y el país es conocido " +
                "por sus paisajes pintorescos, su rica herencia cultural y su diversidad natural.",
                "La ubicación geográfica de Marruecos, en la encrucijada de África y Europa, ha influido " +
                        "en su historia, cultura y economía. El país es conocido por su rica herencia " +
                        "cultural, su arquitectura única y sus paisajes variados.","La ubicación " +
                "geográfica de Colombia ha influido en su diversidad natural, cultural y económica. " +
                "El país es conocido por su biodiversidad, sus paisajes variados y su rica herencia cultural."
                ,"La ubicación geográfica de Venezuela, con su costa caribeña, selvas tropicales y extensas " +
                "llanuras, ha influido en su biodiversidad y en su historia económica, especialmente " +
                "en relación con la industria del petróleo. Sin embargo, el país también ha enfrentado " +
                "desafíos económicos y políticos en las últimas décadas.","La ubicación geográfica de " +
                "Honduras, con su costa en el mar Caribe y su diversidad geográfica, ha influido en " +
                "su ecología, cultura y economía. El país es conocido por su biodiversidad y su herencia cultural rica."
                ,"La situación geográfica de Italia ha influido en su historia, desde el corazón del Imperio " +
                "Romano hasta la cuna del Renacimiento. Además de su importancia histórica y cultural, " +
                "Italia es conocida por su paisaje diverso y su deliciosa cocina","La situación " +
                "geográfica de China ha influido en su historia, cultura y desarrollo económico, y su " +
                "diversidad geográfica la convierte en un país con una gran variedad de paisajes y ecosistemas."
                ,"Bolivia, al igual que otros países de América del Sur, ofrece una geografía variada y una " +
                "rica mezcla de culturas. La posición sin salida al mar de Bolivia ha influido en su " +
                "historia y desarrollo económico.","Brasil es conocido por su diversidad geográfica, " +
                "que va desde selvas tropicales hasta playas, montañas y llanuras. Su posición ecuatorial " +
                "le otorga un clima diverso y le ha valido la reputación de ser uno de los países más biodiversos del planeta."};
        return informaciones;
    }
    private String[] obtenerIdiomas() {
        String[] idiomas = {"El idioma oficial de Uruguay es el español.Uruguay es conocido por su " +
                "alto nivel de alfabetización y el énfasis en la educación, lo que contribuye a una " +
                "población generalmente competente en el uso del idioma español.","En Nicaragua, el " +
                "idioma oficial es el español. La variante del español que se habla en Nicaragua tiene " +
                "algunas peculiaridades y diferencias regionales, pero en general, es parte del grupo " +
                "de dialectos del español centroamericano.", "En Argentina, el idioma oficial es el " +
                "español. Sin embargo, el español que se habla en Argentina tiene características " +
                "distintivas que reflejan la riqueza cultural y lingüística del país. Se conoce comúnmente " +
                "como español rioplatense o castellano rioplatense, y es una variante del español " +
                "que comparte algunas particularidades con el español hablado en Uruguay.","En Ucrania, " +
                "el idioma oficial es el ucraniano. El ucraniano es una lengua eslava oriental que utiliza " +
                "el alfabeto cirílico. Es hablado por la mayoría de la población y es la lengua de " +
                "instrucción en las escuelas y las instituciones oficiales.","En Portugal, el idioma " +
                "oficial es el portugués. El portugués de Portugal es la variante europea del idioma " +
                "y es la lengua predominante en todos los aspectos de la vida cotidiana, la educación " +
                "y los asuntos oficiales. ", "En Rumania, el idioma oficial es el rumano. El rumano " +
                "es una lengua romance y utiliza el alfabeto latino. Es la lengua predominante en la " +
                "comunicación cotidiana, la administración pública, la educación y los medios de " +
                "comunicación en el país.", "En Marruecos, el árabe clásico y el árabe dariya (o " +
                "árabe marroquí) son los idiomas oficiales. El árabe clásico es utilizado en contextos " +
                "formales y oficiales, como en los medios de comunicación y la administración gubernamental."
                ,"En Colombia, el idioma oficial es el español. El español colombiano tiene sus propias " +
                "características lingüísticas y expresiones particulares que lo distinguen, aunque " +
                "comparte muchas similitudes con el español que se habla en otros países de América " +
                "Latina.","En Venezuela, el idioma oficial es el español. El español que se habla en " +
                "Venezuela tiene sus propias peculiaridades y variaciones regionales, pero en general, " +
                "comparte similitudes con el español latinoamericano.","En Honduras, el idioma oficial " +
                "es el español. El español que se habla en Honduras es parte del español centroamericano " +
                "y comparte similitudes con las variantes de otros países de la región.","En Italia, " +
                "el idioma oficial es el italiano. El italiano es una lengua romance que ha evolucionado " +
                "a partir del latín y se habla en todo el país. Además de ser la lengua principal utilizada " +
                "en la comunicación cotidiana, el italiano es el idioma de la literatura, los medios de comunicación " +
                "y la administración pública en Italia.","En China, el idioma oficial es el chino " +
                "estándar o mandarín, conocido como \"Putonghua\" o \"Hanyu\". Es la lengua predominante " +
                "utilizada en la administración gubernamental, la educación y los medios de comunicación en " +
                "todo el país. El chino estándar se basa en la variante hablada en el norte de China y " +
                "se ha establecido como una forma unificada de comunicación para promover la cohesión " +
                "nacional en un país tan diverso.","En Bolivia, el idioma oficial es el español. El " +
                "español que se habla en Bolivia tiene sus propias peculiaridades y variaciones regionales, " +
                "pero en general, comparte similitudes con el español latinoamericano.","\n" +
                "En Brasil, el idioma oficial es el portugués. El portugués brasileño es una variante " +
                "del portugués que ha desarrollado sus propias características fonéticas, léxicas y " +
                "gramaticales a lo largo del tiempo. Es hablado en todo el país y es la lengua de instrucción " +
                "en la educación formal, la administración gubernamental y los medios de comunicación."};
        return idiomas;
    }
}