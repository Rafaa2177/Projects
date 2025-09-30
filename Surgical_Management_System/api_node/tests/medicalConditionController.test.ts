import 'reflect-metadata';

import * as sinon from 'sinon';
import { Response, Request, NextFunction } from 'express';
import { Container } from 'typedi';
import { Result } from '../src/core/logic/Result';
import IMedicalConditionService from "../src/services/IServices/IMedicalConditionService";
import MedicalConditionController from "../src/controllers/medicalConditionController";
import IMedicalConditionDTO from '../src/dto/IMedicalConditionDto';
import { MedicalCondition } from '../src/domain/medicalCondition';

describe('role controller', function () {
  const sandbox = sinon.createSandbox();

  beforeEach(function() {
    Container.reset();
    let medicalConditionSchemaInstance = require("../src/persistence/schemas/medicalConditionSchema").default;
    Container.set("medicalConditionSchema", medicalConditionSchemaInstance);

    let medicalConditionRepo = require("../src/repos/medicalConditionRepo").default;
    let medicalRepoInstance = Container.get(medicalConditionRepo);
    Container.set("MedicalConditionRepo", medicalRepoInstance);

    let medicalServiceClass = require("../src/services/medicalConditionService").default;
    let medicalServiceInstance = Container.get(medicalServiceClass);
    Container.set("MedicalConditionService", medicalServiceInstance);
  });

  afterEach(function() {
    sandbox.restore();
  });

  it('roleController unit test using medicalConditionService stub', async function () {
    // Arrange
    let body = { "name":'role12' };
    let req: Partial<Request> = {};
    req.body = body;
    let res: Partial<Response> = {
      json: sinon.spy()
    };
    let next: Partial<NextFunction> = () => {};

    let roleServiceInstance = Container.get("RoleService");
    sinon.stub(roleServiceInstance, "createRole").returns( Result.ok<IMedicalConditionDTO>( {"id":"123", "name": req.body.name} ));

    const ctrl = new MedicalConditionController(roleServiceInstance as IMedicalConditionService);

    // Act
    await ctrl.createMedicalCondition(<Request>req, <Response>res, <NextFunction>next);

    // Assert
    sinon.assert.calledOnce(res.json);
    sinon.assert.calledWith(res.json, sinon.match({ "id": "123","name": req.body.name}));
  });


  it('roleController + roleService integration test using roleRepoistory and Role stubs', async function () {
    // Arrange
    let body = { "name":'role12' };
    let req: Partial<Request> = {};
    req.body = body;

    let res: Partial<Response> = {
      json: sinon.spy()
    };
    let next: Partial<NextFunction> = () => {};

    sinon.stub(MedicalCondition, "create").returns(Result.ok({"id":"123", "name": req.body.name}));

    let roleRepoInstance = Container.get("RoleRepo");
    sinon.stub(roleRepoInstance, "save").returns(new Promise<MedicalCondition>((resolve, reject) => {
      resolve(MedicalCondition.create({"id":"123", "name": req.body.name}).getValue())
    }));

    let roleServiceInstance = Container.get("RoleService");

    const ctrl = new MedicalConditionController(roleServiceInstance as IMedicalConditionService);

    // Act
    await ctrl.createMedicalCondition(<Request>req, <Response>res, <NextFunction>next);

    // Assert
    sinon.assert.calledOnce(res.json);
    sinon.assert.calledWith(res.json, sinon.match({ "id": "123","name": req.body.name}));
  });


  it('roleController + roleService integration test using spy on roleService', async function () {
    // Arrange
    let body = { "name":'role12' };
    let req: Partial<Request> = {};
    req.body = body;

    let res: Partial<Response> = {
      json: sinon.spy()
    };
    let next: Partial<NextFunction> = () => {};

    let roleRepoInstance = Container.get("RoleRepo");
    sinon.stub(roleRepoInstance, "save").returns(new Promise<MedicalCondition>((resolve, reject) => {
      resolve(MedicalCondition.create({"id":"123", "name": req.body.name}).getValue())
    }));

    let roleServiceInstance = Container.get("RoleService");
    const roleServiceSpy = sinon.spy(roleServiceInstance, "createRole");

    const ctrl = new MedicalConditionController(roleServiceInstance as IMedicalConditionService);

    // Act
    await ctrl.createMedicalCondition(<Request>req, <Response>res, <NextFunction>next);

    // Assert
    sinon.assert.calledOnce(res.json);
    sinon.assert.calledWith(res.json, sinon.match({ "id": "123","name": req.body.name}));
    sinon.assert.calledOnce(roleServiceSpy);
    //sinon.assert.calledTwice(roleServiceSpy);
    sinon.assert.calledWith(roleServiceSpy, sinon.match({name: req.body.name}));
  });


  it('roleController unit test using roleService mock', async function () {
    // Arrange
    let body = { "name":'role12' };
    let req: Partial<Request> = {};
    req.body = body;

    let res: Partial<Response> = {
      json: sinon.spy()
    };
    let next: Partial<NextFunction> = () => {};

    let roleServiceInstance = Container.get("RoleService");
    const roleServiceMock = sinon.mock(roleServiceInstance, "createRole")
    roleServiceMock.expects("createRole")
      .once()
      .withArgs(sinon.match({name: req.body.name}))
      .returns(Result.ok<IMedicalConditionDTO>( {"id":"123", "name": req.body.name} ));

    const ctrl = new MedicalConditionController(roleServiceInstance as IMedicalConditionService);

    // Act
    await ctrl.createMedicalCondition(<Request>req, <Response>res, <NextFunction>next);

    // Assert
    roleServiceMock.verify();
    sinon.assert.calledOnce(res.json);
    sinon.assert.calledWith(res.json, sinon.match({ "id": "123","name": req.body.name}));
  });
});


