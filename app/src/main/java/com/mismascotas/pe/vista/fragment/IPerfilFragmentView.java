package com.mismascotas.pe.vista.fragment;

import java.util.ArrayList;

import com.mismascotas.pe.adapter.RVPAdapter;
import com.mismascotas.pe.model.Mascota;

/**
 * Created by oscarqpe on 23/07/17.
 */

public interface IPerfilFragmentView {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public RVPAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRVP(RVPAdapter adaptador);
}
