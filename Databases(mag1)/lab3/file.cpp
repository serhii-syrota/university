#include "/app/file.h"
#include <iostream>
#include <fstream>

using namespace std;
string read_file(string file_name)
{
  string contents;
  string line;
  ifstream file(file_name);
  cout << "start\n";
  cout << file.good();
  while (getline(file, line))
  {
    contents += line;
  }
  cout << "end\n";
  file.close();
  return contents;
}

void write_file(string file_name, string contents)
{
  ofstream file(file_name);
  if (!file.is_open())
  {
    std::cerr << "Unable to open file" << std::endl;
    return;
  }
  file << contents;
  return;
}
