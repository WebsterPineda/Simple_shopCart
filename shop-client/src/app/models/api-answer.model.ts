export interface ApiAnswer<T> {
  code: number;
  description: string;
  errors?: any[];
  data?: T;
}
