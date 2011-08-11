/*
 * Copyright 2011 Vancouver Ywebb Consulting Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package next.i.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is the model class feeding TableView list rendering via
 * TableController
 */
public class TableData {

	private ArrayList<CellData> _list;

	public TableData() {
		_list = new ArrayList<CellData>();
	}

	public void add(String... titles) {
		if(titles != null){
			for (String s : titles) {
				_list.add(new CellData(s));
			}
		}
//		Collections.addAll(_list, cellData);
	}

	public void add(CellData... cellData) {
		Collections.addAll(_list, cellData);
	}

	public List<CellData> getList() {
		return _list;
	}

}
