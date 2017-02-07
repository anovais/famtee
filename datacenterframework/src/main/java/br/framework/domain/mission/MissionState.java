package br.framework.domain.mission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.framework.domain.DomainTable;
import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description: Tabela de Dominio: A categoria, ou estado da missão : Funcionando, Desativada, Debilitada .. etc
 *
 */
@Entity
public class MissionState extends DomainTable{
}
