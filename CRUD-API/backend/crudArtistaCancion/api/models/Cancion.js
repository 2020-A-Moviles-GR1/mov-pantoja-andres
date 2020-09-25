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
    latitud: {
      type: 'string',
      required: true
    },
    longitud: {
      type: 'string',
      required: true
    },
    imagePath: {
      type: 'string',
      columnName: 'image_path',
      required: true,
      minLength: 3,
    },
    website: {
      type: 'string',
      columnName: 'website',
      required: true,
      minLength: 3,
    },

    artista:{
      model: 'artista',
      required: true
    }

  },

};

