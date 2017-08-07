package com.mismascotas.pe.vista.fragment;

import java.util.ArrayList;

import com.mismascotas.pe.adapter.RVAdapter;
import com.mismascotas.pe.model.Mascota;

/**
 * Created by oscarqpe on 23/07/17.
 */

public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();

    public RVAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(RVAdapter adaptador);
}
