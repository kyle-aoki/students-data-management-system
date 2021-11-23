import { call } from 'redux-saga/effects';
import { StudentsAPI } from 'students-api-client-lib';
import { StudentPage } from 'student-types';

export default function* FindAll() {
  const studentAPI = new StudentsAPI("http://localhost:8080");

  try {
    const result: StudentPage = yield call(studentAPI.findAll);
    console.log(result);
  } catch (e) {
    console.log(e);
  }
}
