/**
 * Cancion.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    titulo: {
      type: 'string',
      minLength: 3,
      required: true
    },
    premiada: {
      type: 'boolean',
      required: true
    },
    fechaLanzamiento: {
      type: 'ref',
      columnName: 'fecha_lanzamiento',
      columnType: 'datetime',
      required: true
    },
    numeroReproducciones: {
      type: 'number',
      columnName: 'numero_reproducciones',
      required: true,
      isInteger: true
    },
    duracionMinutos: {
      type: 'number',
      columnName: 'duracion_minutos',
      required: true,
      columnType: 'FLOAT'
    },

    artista:{
      model: 'artista',
      required: true
    }

  },

};

