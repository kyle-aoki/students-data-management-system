export enum EntityStatus {
  PROVISIONAL = "PROVISIONAL",
  INACTIVE_CLONE = "INACTIVE_CLONE",
  ACTIVE = "ACTIVE",
  DECOMMISSIONED = "DECOMMISSIONED",
}

export interface EntityMetadata {
  entityStatus: EntityStatus;
  createdTimestamp: Date;
  valid: boolean;
  lastEditedTimestamp: Date;
  clonedFrom: string;
}

export interface Name {
  firstName: string;
  lastName: string;
}
export interface Student {
  studentId: string;
  entityMetadata: EntityMetadata;
  name: Name;
  birthday: Date;
  enrollmentDate: Date;
}

export interface StudentPage {
  content: Student[];
  pageable: {
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  };
  last: boolean;
  totalPages: number;
  totalElements: number;
  size: number;
  sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
  number: number;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}
