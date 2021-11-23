import { takeEvery } from "redux-saga/effects";
import FindAll from "./find-all";

export default function* TableSaga() {
  yield takeEvery("USER_FETCH_REQUESTED", FindAll);
}
