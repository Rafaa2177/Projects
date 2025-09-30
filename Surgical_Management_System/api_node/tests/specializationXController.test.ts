import 'reflect-metadata';
import * as sinon from 'sinon';
import { Response, Request, NextFunction } from 'express';
import { Container } from 'typedi';
import { Result } from '../src/core/logic/Result';
import SpecializationController from "../src/controllers/specializationController";
import ISpecializationService from "../src/services/IServices/ISpecializationService";
import { Specialization } from "../src/domain/specialization";
import ISpecializationDTO from "../src/dto/ISpecializationDTO";

describe('specialization controller', function () {
  const sandbox = sinon.createSandbox();

  beforeEach(function() {
    Container.reset();

    const specializationSchemaInstance = require("../src/persistence/schemas/specializationSchema").default;
    Container.set("specializationSchema", specializationSchemaInstance);

    const specializationRepoClass = require("../src/repos/specializationRepo").default;
    const specializationRepoInstance = Container.get(specializationRepoClass);
    Container.set("SpecializationRepo", specializationRepoInstance);

    const specializationServiceClass = require("../src/services/specializationService").default;
    const specializationServiceInstance = Container.get(specializationServiceClass);
    Container.set("SpecializationService", specializationServiceInstance);
  });

  afterEach(function() {
    sandbox.restore();
  });

  it('specializationController unit test using specializationService stub', async function () {
    const body = { "name": 'Cardiology', "code": '12345' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy() };
    const next: Partial<NextFunction> = () => {};

    const specializationServiceInstance = Container.get("SpecializationService") as ISpecializationService;
    const stubCreate = sinon.stub(specializationServiceInstance as any, "createSpecialization");
    stubCreate.returns(Result.ok<ISpecializationDTO>({
      "id": "123",
      "name": req.body.name,
      "code": req.body.code
    }));

    const ctrl = new SpecializationController(specializationServiceInstance);

    await ctrl.createSpecialization(<Request>req, <Response>res, <NextFunction>next);

    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(
      res.json as sinon.SinonSpy,
      sinon.match({ "id": "123", "name": req.body.name, "code": req.body.code })
    );
  });

  it('specializationController + specializationService integration test using specializationRepository and Specialization stubs', async function () {
    const body = { "name": 'Cardiology', "code": '12345' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = {
      json: sinon.spy(),
      status: sinon.spy(),
      send: sinon.spy()
    };
    const next: Partial<NextFunction> = sinon.spy();

    const specialization = Specialization.create({
      id: "123",
      name: req.body.name,
      code: req.body.code,
      description: ""
    });

    // Make sure we're actually getting a Result.ok
    console.log('Specialization create result:', specialization);

    const createStub = sinon.stub(Specialization, "create").returns(specialization);

    const specializationRepoInstance = Container.get("SpecializationRepo");
    const saveStub = sinon.stub(specializationRepoInstance as any, "save");
    saveStub.returns(Promise.resolve(specialization.getValue()));

    const specializationServiceInstance = Container.get("SpecializationService");
    const createSpecializationSpy = sinon.spy(specializationServiceInstance as any, "createSpecialization");

    const ctrl = new SpecializationController(specializationServiceInstance as ISpecializationService);

    try {
      await ctrl.createSpecialization(<Request>req, <Response>res, <NextFunction>next);

      console.log('Create specialization completed');
      console.log('res.json called:', (res.json as sinon.SinonSpy).callCount, 'times');
      console.log('res.json calls:', (res.json as sinon.SinonSpy).getCalls().map(call => call.args));
      console.log('service called:', createSpecializationSpy.callCount, 'times');
      console.log('next called:', (next as sinon.SinonSpy).callCount, 'times');

      sinon.assert.calledOnce(res.json as sinon.SinonSpy);
      sinon.assert.calledWith(
        res.json as sinon.SinonSpy,
        sinon.match({ "name": req.body.name, "code": req.body.code })
      );
    } catch (error) {
      console.error('Test error:', error);
      throw error;
    }
  });

  it('specializationController + specializationService integration test using spy on specializationService', async function () {
    const body = { "name": 'Cardiology', "code": '12345' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy() };
    const next: Partial<NextFunction> = () => {};

    const specialization = Specialization.create({
      id: "123",
      name: req.body.name,
      code: req.body.code,
      description: ""
    });

    const specializationRepoInstance = Container.get("SpecializationRepo");
    const saveStub = sinon.stub(specializationRepoInstance as any, "save");
    saveStub.returns(Promise.resolve(specialization));

    const specializationServiceInstance = Container.get("SpecializationService") as ISpecializationService;
    const specializationServiceSpy = sinon.spy(specializationServiceInstance as any, "createSpecialization");

    const ctrl = new SpecializationController(specializationServiceInstance);

    await ctrl.createSpecialization(<Request>req, <Response>res, <NextFunction>next);

    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(
      res.json as sinon.SinonSpy,
      sinon.match({ "name": req.body.name, "code": req.body.code })
    );
    sinon.assert.calledOnce(specializationServiceSpy);
    sinon.assert.calledWith(
      specializationServiceSpy,
      sinon.match({ name: req.body.name, code: req.body.code })
    );
  });

  it('specializationController unit test using specializationService mock', async function () {
    const body = { "name": 'Cardiology', "code": 'CARDIOLOGY_001' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy() };
    const next: Partial<NextFunction> = () => {};

    const specializationServiceInstance = Container.get("SpecializationService") as ISpecializationService;
    const specializationServiceMock = sinon.mock(specializationServiceInstance);
    specializationServiceMock.expects("createSpecialization")
      .once()
      .withArgs(sinon.match({ name: req.body.name, code: req.body.code }))
      .returns(Result.ok<ISpecializationDTO>({ "id": "123", "name": req.body.name, "code": req.body.code }));

    const ctrl = new SpecializationController(specializationServiceInstance);

    await ctrl.createSpecialization(<Request>req, <Response>res, <NextFunction>next);

    specializationServiceMock.verify();
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(
      res.json as sinon.SinonSpy,
      sinon.match({ "id": "123", "name": req.body.name, "code": req.body.code })
    );
  });
});
