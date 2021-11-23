import { Student, StudentPage } from "student-types";

export class StudentsAPI {
  host: string;
  standardOptions: RequestInit;

  constructor(host: string) {
    this.host = host + "/students";
    this.standardOptions = {
      headers: { "Content-Type": "application/json" },
    };
  }

  URL(uri: string) {
    return this.host + uri;
  }

  async getJson(url: string, options: RequestInit) {
    const response = await fetch(url, options);
    const json = await response.json();
    return json;
  }

  async create(): Promise<Student> {
    return this.getJson(this.URL("/create"), {
      method: "POST",
      ...this.standardOptions,
    });
  }

  // @PutMapping("/save")

  // @GetMapping("/find")

  // @PutMapping("/activate")

  async findAll(): Promise<StudentPage> {
    return this.getJson(this.URL("/find-all"), {
      method: "POST",
      ...this.standardOptions,
    });
  }

  // @PutMapping("/create-inactive-clone")
}
