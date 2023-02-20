import EndpointInterface from "#{API_BASE_URL}/api/rest/endpoint/EndpointInterface.js";

// the request schema, this should be updated
// whenever changes to the endpoint parameters are made
// this is important because this is used to validate and parse the request parameters
const requestSchema = {
  "title" : "createAddressRequest",
  "id" : "createAddressRequest",
  "default" : "Schema definition for createAddress",
  "$schema" : "http://json-schema.org/draft-07/schema",
  "type" : "object",
  "properties" : {
    "walletId" : {
      "title" : "walletId",
      "type" : "string",
      "minLength" : 1
    },
    "notes" : {
      "title" : "notes",
      "type" : "string",
      "minLength" : 1
    },
    "city" : {
      "title" : "city",
      "type" : "string",
      "minLength" : 1
    },
    "dialCode" : {
      "title" : "dialCode",
      "type" : "string",
      "minLength" : 1
    },
    "postalCode" : {
      "title" : "postalCode",
      "type" : "string",
      "minLength" : 1
    },
    "latitude" : {
      "title" : "latitude",
      "type" : "string",
      "minLength" : 1
    },
    "isDefault" : {
      "title" : "isDefault",
      "type" : "string",
      "minLength" : 1
    },
    "phoneNumber" : {
      "title" : "phoneNumber",
      "type" : "string",
      "minLength" : 1
    },
    "streetAddress" : {
      "title" : "streetAddress",
      "type" : "string",
      "minLength" : 1
    },
    "countryCode" : {
      "title" : "countryCode",
      "type" : "string",
      "minLength" : 1
    },
    "name" : {
      "title" : "name",
      "type" : "string",
      "minLength" : 1
    },
    "state" : {
      "title" : "State",
      "description" : "State",
      "id" : "State",
      "storages" : [ "SQL" ],
      "type" : "object",
      "properties" : {
        "country" : {
          "title" : "State.country",
          "description" : "Country",
          "id" : "CE_State_country",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "$ref" : "#/definitions/org.meveo.model.billing.Country"
        },
        "stateName" : {
          "title" : "State.stateName",
          "description" : "StateName",
          "id" : "CE_State_stateName",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 90
        },
        "active" : {
          "title" : "State.active",
          "description" : "Active",
          "id" : "CE_State_active",
          "storages" : [ "SQL" ],
          "default" : "true",
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "boolean"
        },
        "stateCode" : {
          "title" : "State.stateCode",
          "description" : "StateCode",
          "id" : "CE_State_stateCode",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 6
        },
        "id" : {
          "title" : "State.id",
          "description" : "Id",
          "id" : "CE_State_id",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "integer"
        },
        "odooCountryId" : {
          "title" : "State.odooCountryId",
          "description" : "Odoo Country Id",
          "id" : "CE_State_odooCountryId",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "integer"
        },
        "countryStateUniqueHash" : {
          "title" : "State.countryStateUniqueHash",
          "description" : "Coutry State Unique Hash",
          "id" : "CE_State_countryStateUniqueHash",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 255
        }
      }
    },
    "longitude" : {
      "title" : "longitude",
      "type" : "string",
      "minLength" : 1
    }
  }
}

// the response schema, this should be updated
// whenever changes to the endpoint parameters are made
// this is important because this could be used to parse the result
const responseSchema = {
  "title" : "createAddressResponse",
  "id" : "createAddressResponse",
  "default" : "Schema definition for createAddress",
  "$schema" : "http://json-schema.org/draft-07/schema",
  "type" : "object",
  "properties" : {
    "result" : {
      "title" : "result",
      "type" : "string",
      "minLength" : 1
    }
  }
}

// should contain offline mock data, make sure it adheres to the response schema
const mockResult = {};

class createAddress extends EndpointInterface {
	constructor() {
		// name and http method, these are inserted when code is generated
		super("createAddress", "POST");
		this.requestSchema = requestSchema;
		this.responseSchema = responseSchema;
		this.mockResult = mockResult;
	}

	getRequestSchema() {
		return this.requestSchema;
	}

	getResponseSchema() {
		return this.responseSchema;
	}
}

export default new createAddress();