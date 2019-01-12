package me.arun.arunrxjavaexploring.RoomDB;
/**
 * Created by arun on 28/06/18.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.RoomDB.models.Student;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>
{
    private Context context;
    List<Student> studentList;
    private StudentAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName, tvStreet;
        Button btUpdate, btDelete;

        public MyViewHolder(View view)
        {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvStreet = (TextView) view.findViewById(R.id.tvAddress);
            btDelete = (Button) view.findViewById(R.id.btDelete);
            btUpdate = (Button) view.findViewById(R.id.btUpdate);
            btUpdate.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    listener.onTicketUpdate(studentList.get(getAdapterPosition()));
                }
            });

            btDelete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    listener.onTicketDelete(studentList.get(getAdapterPosition()));
                }
            });

            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    // send selected student in callback
                    listener.onTicketUpdate(studentList.get(getAdapterPosition()));
//
                }
            });
        }
    }

    public StudentAdapter(Context context, List<Student> studentList, StudentAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.studentList = studentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_student, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
         Student student=studentList.get(position);
         holder.tvStreet.setText(student.Address);
         holder.tvName.setText(student.Name);

    }

    @Override
    public int getItemCount() {

        return studentList.size();
    }

    public void upDate(List<Student> studentList){
        this.studentList=studentList;
        notifyDataSetChanged();
    }



    public interface StudentAdapterListener {
        void onTicketDelete(Student student);

        void onTicketUpdate(Student student);

    }
}
