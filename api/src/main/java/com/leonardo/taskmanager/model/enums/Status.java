package com.leonardo.taskmanager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Status {
    NOVO                ("Novo"),
    PENDENTE            ("Pendente"),
    EM_EXECUCAO         ("Em execução"),
    EM_EXECUCAO_PAUSA   ("Em execução (Pausa)"),
    CONCLUIDO           ("Concluído"),
    INATIVO             ("Inátivo");

    private final String friendlyName;
}
