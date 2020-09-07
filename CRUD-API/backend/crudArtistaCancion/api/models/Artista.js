/**
 * Artista.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre: {
      type: 'string',
      minLength: 3,
      required: true
    },
    banda: {
      type: 'boolean',
      required: true
    },
    fechaInicio: {
      type: 'ref',
      columnName: 'fecha_inicio',
      columnType: 'datetime',
      required: true
    },
    cantidadDiscos: {
      type: 'number',
      columnName: 'cantidad_discos',
      required: true,
      isInteger: true
    },
    gananciaTotal: {
      type: 'number',
      columnName: 'ganancia_total',
      required: true,
      columnType: 'FLOAT'
    },

    canciones:{
      collection: 'cancion',
      via: 'artista' 
    }



  },

};

