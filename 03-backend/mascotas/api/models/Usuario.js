/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  tableName: 'epn_usuario',

  attributes: {
    cedula: { // nombre del atributo
      type: 'string',
      required: true,
      columnName: 'epn_cedula',
      unique: true,
      minLength: 10,
      maxLength: 25
    },
    nombre: {
      type: 'string',
      minLength: 3,
      required: true // por defecto false
    },
    correo: {
      type: 'string',
      isEmail: true
    },
    estadoCivil: {
      type: 'string',
      isIn: ['Soltero', 'Casado', 'Divorciado', 'Viudo', 'Unión Libre'],
      columnName: 'estado_civil',
      defaultsTo: 'Soltero'

    },
    password: {
      type: 'string',
      regex: /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/
    },

    pokemons: { //One to Many, por eso en plural
      collection: 'pokemon', // Referencia al modelo hijo,
      via: 'usuario' // nombre de la FK
    }


  },

};

