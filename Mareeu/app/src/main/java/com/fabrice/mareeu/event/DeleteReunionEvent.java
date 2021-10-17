package com.fabrice.mareeu.event;

import com.fabrice.mareeu.model.Reunion;

public class DeleteReunionEvent {

    public Reunion reunion;

    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}
